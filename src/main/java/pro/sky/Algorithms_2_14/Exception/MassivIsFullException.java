package pro.sky.Algorithms_2_14.Exception;

public class MassivIsFullException extends RuntimeException {
    public MassivIsFullException() {
    }

    public MassivIsFullException(String message) {
        super(message);
    }

    public MassivIsFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public MassivIsFullException(Throwable cause) {
        super(cause);
    }

    public MassivIsFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
