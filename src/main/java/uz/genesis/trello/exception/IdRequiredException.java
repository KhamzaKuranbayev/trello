package uz.genesis.trello.exception;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 10/5/18.
 */

public class IdRequiredException extends GenericRuntimeException {

    public IdRequiredException(String message) {
        super(message);
    }


    public IdRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

}
