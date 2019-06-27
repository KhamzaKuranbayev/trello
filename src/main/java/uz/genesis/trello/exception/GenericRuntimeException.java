package uz.genesis.trello.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 10/5/18.
 */

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GenericRuntimeException extends RuntimeException {

    public GenericRuntimeException(String message) {
        super(message);
    }

//    public GenericRuntimeException(ErrorMessage message) {
//        super(message.getMessage());
//    }

    public GenericRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

//    public GenericRuntimeException(ErrorMessage message, Throwable cause) {
//        super(message.getMessage(), cause);
//    }
}
