package ex;

public class TokenizerException extends ApplicationRuntimeException {
    public TokenizerException() {
    }

    public TokenizerException(String message) {
        super(message);
    }

    public TokenizerException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenizerException(Throwable cause) {
        super(cause);
    }
}
