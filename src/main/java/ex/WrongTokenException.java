package ex;

public class WrongTokenException extends IncorrectInputException {
    public WrongTokenException() {
    }

    public WrongTokenException(String message) {
        super(message);
    }

    public WrongTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongTokenException(Throwable cause) {
        super(cause);
    }
}
