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
   private Time receivedTime;

   @OneToOne
   @JoinColumn(name = "fk_userid")
   private User sentBy;

   @OneToOne
   @JoinColumn(name = "fk_chatRoomid")
   private Chat chatRoom;

   public ChatMessage(String content, Time sendTime, User sentBy, Chat chatRoom) {
      this.content = content;
      this.sendTime = sendTime;
      this.sentBy = sentBy;
      this.chatRoom = chatRoom;
   }

   public String getContent() {
      return content;
   }

   public Time getSendTime() {
      return sendTime;
   }

   public Time getReceivedTime() {
      return receivedTime;
   }

   public User getSentUser() {
      return sentBy;
   }

   public Chat getChatRoom() {
      return chatRoom;
   }

   public void setReceivedTime(Time receivedTime) {
      this.receivedTime = receivedTime;
   }
}