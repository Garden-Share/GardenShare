package re.gardensha.gardenshare;

public class InvalidReviewException extends Exception {
    static final long serialVersionUID = 0l;
    public InvalidReviewException(String message){
        super(message);
    }
}