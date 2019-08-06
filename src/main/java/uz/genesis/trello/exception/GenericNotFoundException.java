package uz.genesis.trello.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 10/5/18.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenericNotFoundException extends GenericRuntimeException {

    public GenericNotFoundException(String message) {
        super(message);
    }

    public GenericNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
