package uz.genesis.trello.config.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.exception.CustomSqlException;

/**
 * Created by 'javokhir' on 12/06/2019
 */

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<DataDto<?>> handleCustomException(Exception ex, WebRequest request) {
        String message = getLastCause(ex);
        logger.error(message, ex);
        return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                message).build()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public final ResponseEntity<DataDto<?>> handleUserNotFoundException(UnauthorizedUserException ex, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                ex.getMessage()).build()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomSqlException.class)
    public final ResponseEntity<DataDto<?>> handleSqlException(CustomSqlException ex, WebRequest request) {
        String message = ex.getFriendlyMessage();
        logger.error(ex.getMessage(), ex);
//        logger.error("#requestBody: " + request.get);
        return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                message).systemName(ex.getSystemMessage()).build()), HttpStatus.UNAUTHORIZED);
    }

    private String getLastCause(Throwable throwable) {
        return throwable.getCause() == null ? (throwable.getLocalizedMessage() == null ? throwable.getMessage()
                : throwable.getLocalizedMessage()) : getLastCause(throwable.getCause());
    }
    /*@ExceptionHandler(RefreshTokenExpiredException.class)
    public final ResponseEntity<DataDto<?>> handleUserNotFoundException(RefreshTokenExpiredException ex, WebRequest request) {
        logger.error(ex);
        return new ResponseEntity<>(new DataDto<>(ex.getCause(), new AppErrorDto(HttpStatus.FORBIDDEN.value(),
                ex.getMessage(), false), HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(CustomSqlException.class)
    public final DataDto<?> handleUserNotFoundException(CustomSqlException ex, WebRequest request) {
        logger.error( ex);
        return new DataDto<>(null, new AppErrorDto(ex.getFriendlyMessage(), ex.getSystemMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public final ResponseEntity<DataDto<?>> handleUserNotFoundException(UnauthorizedUserException ex, WebRequest request) {
        logger.error(ex);
        return new ResponseEntity<>(new DataDto<>(null, new AppErrorDto(ex.getMessage(), ex.getMessage(),
                HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<DataDto<?>> handleUserNotFoundException(AccessDeniedException ex, WebRequest request) {
        logger.error(ex);
        return new ResponseEntity<>(new DataDto<>(null, new AppErrorDto(ex.getMessage(), ex.getMessage(),
                HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN.value()), HttpStatus.FORBIDDEN);
    }*/

    /*@ExceptionHandler(IllegalStateException.class)
    public final DataDto<?> handleUserNotFoundException(IllegalStateException ex, WebRequest request) {
        return null;
    }*/

    /*public String getMessage(CustomSqlException ex) {
        String message = ex.getMessage();

        if (message != null && message.contains("ORA")) {
            message = getErrorMessageSplitted(message);
        } else if (ex.getCause() != null) {
            message = ex.getCause().getMessage();

            if (message != null && message.contains("ORA")) {
                message = getErrorMessageSplitted(message);
            }
        }
        if(message != null)
            message = message.trim();

        return message;
    }

    private String getErrorMessageSplitted(String message) {
        String[] messages = message.split(":");

        if (messages.length > 1) {
            String[] messageCode = messages[1].split("\n");
            String errorMessage = messageCode[0];
            String errorCode = messageCode[1];

            return errorMessage;
        }

        return message;
    }*/
}
