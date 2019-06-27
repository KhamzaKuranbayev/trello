package uz.genesis.trello.exception;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 10/5/18.
 */

public class RequestObjectNullPointerException extends RuntimeException {

    public RequestObjectNullPointerException(String message) {
        super(message);
    }

    public RequestObjectNullPointerException(String message, Throwable cause) {
        super(message, cause);
    }
}
