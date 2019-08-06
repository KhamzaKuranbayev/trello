package uz.genesis.trello.exception;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 10/5/18.
 */

public class RequestObjectNullPointerException extends GenericRuntimeException {

    public RequestObjectNullPointerException(String message) {
        super(message);
    }

    public RequestObjectNullPointerException(String message, String key) {
        super(message, key);
    }

    public RequestObjectNullPointerException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestObjectNullPointerException(String message, Throwable cause, String key) {
        super(message, cause, key);
    }
}
