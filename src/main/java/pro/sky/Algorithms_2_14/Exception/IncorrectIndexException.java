package pro.sky.Algorithms_2_14.Exception;

public class IncorrectIndexException extends RuntimeException {
    public IncorrectIndexException() {
    }

    public IncorrectIndexException(String message) {
        super(message);
    }

    public IncorrectIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectIndexException(Throwable cause) {
        super(cause);
    }

    public IncorrectIndexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
