package uz.genesis.trello.config.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.exception.CustomSqlException;
import uz.genesis.trello.exception.ValidationException;

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
                message).systemName(ex.getLocalizedMessage()).build()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<DataDto<?>> handleCustomException(AccessDeniedException ex, WebRequest request) {
        String message = getLastCause(ex);
//        logger.error(message, ex);
        return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                message).systemName(ex.getLocalizedMessage()).build()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    public final ResponseEntity<DataDto<?>> handleUserNotFoundException(UnauthorizedUserException ex, WebRequest request) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                ex.getMessage()).systemName(ex.getLocalizedMessage()).build()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(CustomSqlException.class)
    public final ResponseEntity<DataDto<?>> handleSqlException(CustomSqlException ex, WebRequest request) {
        String message = ex.getFriendlyMessage();
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                message).systemName(ex.getSystemMessage()).build()), ex.getHttpStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<DataDto<?>> handleValidationException(ValidationException ex, WebRequest request) {
        String message = getLastCause(ex);
        logger.error(message, ex);
        return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                message).systemName(ex.getLocalizedMessage()).build()), HttpStatus.BAD_REQUEST);
    }

    private String getLastCause(Throwable throwable) {
        return throwable.getCause() == null ? (throwable.getLocalizedMessage() == null ? throwable.getMessage()
                : throwable.getLocalizedMessage()) : getLastCause(throwable.getCause());
    }
}
