package re.gardensha.gardenshare;

import java.util.Date;

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
   private Date lastMsg;
   private String userOneName;
   private String userTwoName;
   // private boolean read;

   public Chat() {
   }

   public Chat(String userOne, String userTwo, Date lastMsg) {
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

   public String getOtherUser(String oAuthId) {
      if (this.userOne.equals(oAuthId))
         return this.userTwo;
      else
         return this.userOne;
   }

   public String getSender() {
      return userOne;
   }

   public String getReceiver() {
      return this.userTwo;
   }

   public Date getLastMsg() {
      return lastMsg;
   }

   public void setName(String id, String name) {
      if (id.equals(userOne))
         this.userOneName = name;
      else
         this.userTwoName = name;
   }

   public String getOtherUserName(String user) {
      if (this.userOneName.equals(user))
         return this.userTwoName;
      else
         return this.userOneName;
   }

   public String getOtherUserNameByAuthId(String user) {
      if (this.userOne.equals(user))
         return this.userTwoName;
      else
         return this.userOneName;
   }

}