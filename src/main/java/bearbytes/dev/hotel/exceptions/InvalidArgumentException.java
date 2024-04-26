package bearbytes.dev.hotel.exceptions;

public class InvalidArgumentException extends Exception{
    public InvalidArgumentException() {}

    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

}
