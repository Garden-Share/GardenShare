package re.gardensha.gardenshare;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chat {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   private String userOne;
   private String userTwo;
   private Time lastMsg;
   // private boolean read;

   public Chat() {
   }

   public Chat(String userOne, String userTwo, Time lastMsg) {
      this.userOne = userOne;
      this.userTwo = userTwo;
      this.lastMsg = lastMsg;
   }

   public Integer getId() {
      return id;
   }

   public String getUser(String oAuthId) {
      if (this.userOne.equals(oAuthId))
         return this.userOne;
      else
         return this.userTwo;
   }

   public String getSender() {
      return userOne;
   }

   public String getReceiver() {
      return this.userTwo;
   }

   public Time getLastMsg() {
      return lastMsg;
   }

}