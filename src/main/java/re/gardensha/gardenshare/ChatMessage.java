package re.gardensha.gardenshare;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ChatMessage {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   public Integer id;
   private String content;
   private Time sendTime;

   @OneToOne
   @JoinColumn(name = "fk_sender")
   private User sender;

   @OneToOne
   @JoinColumn(name = "fk_receiver")
   private User receiver;

   @OneToOne
   @JoinColumn(name = "fk_chatroomid")
   private Chat chatRoom;

   public ChatMessage(String content, Time sendTime, User sender, User receiver, Chat chatRoom) {
      this.content = content;
      this.sendTime = sendTime;
      this.sender = sender;
      this.chatRoom = chatRoom;
      this.receiver = receiver;
   }

   public String getContent() {
      return content;
   }

   public Time getSendTime() {
      return sendTime;
   }

   public User getSentUser() {
      return sender;
   }

   public Chat getChatRoom() {
      return chatRoom;
   }
}