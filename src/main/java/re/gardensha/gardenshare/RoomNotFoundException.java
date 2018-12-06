package re.gardensha.gardenshare;

public class RoomNotFoundException extends Exception {
   static final long serialVersionUID = 0l;

   public RoomNotFoundException(String message) {
      super(message);
   }
}