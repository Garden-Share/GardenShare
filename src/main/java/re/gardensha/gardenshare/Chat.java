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
   private String me;
   private String you;
   private Time lastMsg;

   public Chat() {
   }

   public Chat(String me, String you, Time lastMsg) {
      this.me = me;
      this.you = you;
      this.lastMsg = lastMsg;

   }

   public int getId() {
      return id;
   }

   public String getReceiver() {
      return you;
   }

   public String getSender() {
      return me;
   }

   public Time getLastMsg() {
      return lastMsg;
   }

}