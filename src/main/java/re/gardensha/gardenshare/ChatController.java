package re.gardensha.gardenshare;

import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController extends GardenShareController {

   @Autowired
   private UserRepository userRepo;

   @Autowired
   private ChatRepository chatRepo;

   @Autowired
   private ChatMessageRepository chatMsgRepo;

   private static String chatRoomObjectName = "chatroom";
   private static String chatMessageObjectName = "message";
   private static String userObjectName = "userPage";

   @GetMapping(path = "/message")
   public ModelAndView getMessage(Principal userPrincipal, HttpServletResponse res) {
      ModelAndView result = new ModelAndView("message");
      System.out.println("hello?");

      List<Chat> chatRoom = chatRepo.findChatRoomByOauthId(userPrincipal.getName());
      if (!chatRoom.isEmpty()) {
         result.addObject(chatRoomObjectName, chatRoom);
         return result;
      }

      return result;
   }

   // sending message
   @PostMapping(value = "/message/{chatid}")
   public ModelAndView sendMessage(@PathVariable(value = "chatid") int chatId,
         @RequestParam(value = "content") String content, Principal userPrincipal, HttpServletResponse res)
         throws RoomNotFoundException, InvalidUserException, IOException {
      ModelAndView result = new ModelAndView("eachMsg");
      Optional<Chat> room = chatRepo.findById(chatId);
      if (!room.isPresent()) {
         throw new RoomNotFoundException("The room has been deleted or does not exist.");
      }

      Date time = Calendar.getInstance().getTime();
      Chat curRoom = room.get();

      User sender = findUser(userPrincipal.getName());
      User receiver = findUser(curRoom.getOtherUser(userPrincipal.getName()));

      ChatMessage newChatMsg = new ChatMessage(content, time, sender, receiver, curRoom);
      chatMsgRepo.save(newChatMsg);
      result.addObject(chatMessageObjectName, newChatMsg);
      res.sendRedirect("/message/" + chatId);

      return result;
   }

   @GetMapping(path = "/message/{chatid}")
   public ModelAndView getMessage(@PathVariable("chatid") int chatId, Principal userPrincipal,
         HttpServletResponse res) {
      ModelAndView result = new ModelAndView("eachMsg");

      List<Chat> rooms = chatRepo.findChatRoomByOauthId(userPrincipal.getName());

      if (!rooms.isEmpty()) {
         result.addObject(chatRoomObjectName, rooms);
      }

      List<ChatMessage> msgs = chatMsgRepo.findMessageByRoomId(chatId);
      result.addObject(chatMessageObjectName, msgs);

      return result;
   }

   @RequestMapping(value = "/chat/compose/{id}")
   public ModelAndView getMsgToUser(@PathVariable("id") int userId, HttpServletResponse res) throws IOException {
      ModelAndView result = new ModelAndView("chat/compose");
      Optional<User> possibleUser = userRepo.findById(userId);
      if (!possibleUser.isPresent()) {
         res.sendError(404, "No user with that id was found");
         return null;
      }

      User user = possibleUser.get();
      result.addObject(userObjectName, user);
      return result;
   }

   // When sending the first message
   @PostMapping(path = "/chat/compose/{id}")
   public ModelAndView createNewRoom(Principal user, @RequestParam(value = "id") int id,
         @RequestParam(value = "content") String content, HttpServletResponse res)
         throws InvalidUserException, IOException {

      ModelAndView result = new ModelAndView("chat/compose");

      Optional<User> otherUser = userRepo.findById(id);

      if (otherUser.isPresent()) {
         Date time = Calendar.getInstance().getTime();
         String userTwoOauthId = otherUser.get().getOAuthId();
         User me = findUser(user.getName());

         Chat room = new Chat(user.getName(), userTwoOauthId, time);
         room.setName(user.getName(), me.getName());
         room.setName(userTwoOauthId, otherUser.get().getName());

         chatRepo.save(room);
         List<User> curUser = userRepo.findUserByOauthId(user.getName());
         if (curUser.size() != 1)
            throw new InvalidUserException("User not found");

         ChatMessage newMsg = new ChatMessage(content, time, curUser.get(0), otherUser.get(), room);
         chatMsgRepo.save(newMsg);

         result.addObject(chatRoomObjectName, room);
         result.addObject(chatMessageObjectName, newMsg);
         res.sendRedirect("/user/" + id);
         return result;
      } else {
         throw new InvalidUserException("Could not find a valid user");

      }

   }

   private User findUser(String id) throws InvalidUserException {
      List<User> possibleUser = userRepo.findUserByOauthId(id);
      if (possibleUser.size() != 1) {
         throw new InvalidUserException("Could not find creator as valid user in database");
      }

      User user = possibleUser.get(0);

      return user;
   }
}