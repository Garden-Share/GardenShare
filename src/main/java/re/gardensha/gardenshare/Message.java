package re.gardensha.gardenshare;

public class Message {

   private String content;
   private String toUserId;

   public Message(String content, String userId) {
      this.content = content;
      this.toUserId = userId;
   }

   public String getContent() {
      return this.content;
   }

   public String getUserId() {
      return this.toUserId;
   }

}