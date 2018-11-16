package re.gardensha.gardenshare;

public class InvalidListingException extends Exception {
    static final long serialVersionUID = 0l;
    public InvalidListingException(String message){
        super(message);
    }
}