package re.gardensha.gardenshare;

public class InvalidUserException extends Exception {
   static final long serialVersionUID = 0l;

   public InvalidUserException(String message) {
      super(message);
   }
}