package re.gardensha.gardenshare;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

   @RequestMapping("/message")
   public String message() {
      return "message";
   }

   @GetMapping(path = "/message")
   public ModelAndView getMessage(Principal userPrincipal, HttpServletResponse res) {
      ModelAndView result = new ModelAndView("message/room");

      List<Chat> chatRoom = chatRepo.findChatRoomByOauthId(userPrincipal.getName());
      if (!chatRoom.isEmpty()) {
         result.addObject(chatRoomObjectName, chatRoom);
         return result;
      }

      return null;
   }

   // sending message
   @PostMapping(value = "/message/{chatid}")
   public ModelAndView sendMessage(@PathVariable(value = "chatid") int chatId, Principal userPrincipal,
         @RequestBody Message msg, HttpServletResponse res) throws RoomNotFoundException, InvalidUserException {
      ModelAndView result = new ModelAndView("message/" + chatId);

      Optional<Chat> room = chatRepo.findById(chatId);
      if (!room.isPresent()) {
         throw new RoomNotFoundException("The message has been deleted or does not exist.");
      }

      Date date = new Date();
      Chat curRoom = room.get();

      User sender = findUser(userPrincipal.getName());
      User receiver = findUser(curRoom.getUser(msg.getUserId()));

      ChatMessage newChatMsg = new ChatMessage(msg.getContent(), new Time(date.getTime()), sender, receiver, curRoom);
      chatMsgRepo.save(newChatMsg);
      result.addObject(chatMessageObjectName, newChatMsg);

      return result;
   }

   @GetMapping(value = "/message/{chatid}")
   public ModelAndView getMessage(@PathVariable(value = "chatid") int chatId, HttpServletResponse res) {
      ModelAndView result = new ModelAndView("message/" + chatId);

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
         Date date = new Date();
         Time cur = new Time(date.getTime());
         String userTwoOauthId = otherUser.get().getOAuthId();

         Chat room = new Chat(user.getName(), userTwoOauthId, cur);

         chatRepo.save(room);
         List<User> curUser = userRepo.findUserByOauthId(user.getName());
         if (curUser.size() != 1)
            throw new InvalidUserException("User not found");

         ChatMessage newMsg = new ChatMessage(content, cur, curUser.get(0), otherUser.get(), room);
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