package CustomException;

public class OutOfRange extends RuntimeException{
    public OutOfRange() {
    }

    public OutOfRange(String message) {
        super(message);
    }

    public OutOfRange(String message, Throwable cause) {
        super(message, cause);
    }
}
