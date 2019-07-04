package uz.genesis.trello.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 10/5/18.
 */

@Getter
@Setter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CustomSqlException extends RuntimeException {

    /**
     * Common logger for use in subclasses.
     */
    protected final Log logger = LogFactory.getLog(getClass());

    private Integer sqlErrorCode;
    private String friendlyMessage;
    private String systemMessage;

    public CustomSqlException(String message) {
        super(message);
        initMessage();
    }

    public CustomSqlException(String message, Integer sqlErrorCode) {
        super(message);
        this.sqlErrorCode = sqlErrorCode;
        initMessage();
    }

    public CustomSqlException(String message, Throwable cause, Integer sqlErrorCode) {
        super(message, cause);
        this.sqlErrorCode = sqlErrorCode;
        initMessage();
    }

    public CustomSqlException(String message, Throwable cause) {
        super(message, cause);
        initMessage();
    }

    private void initMessage() {
        friendlyMessage = null;
        systemMessage = null;

        String message = super.getMessage();

        systemMessage = message.trim();

        try {
            friendlyMessage = message.substring(message.lastIndexOf("detail:") + 7, message.indexOf("hint:")).trim();
            if (friendlyMessage.isEmpty()) {
                friendlyMessage = message.substring(message.lastIndexOf("message:") + 8, message.indexOf("detail:")).trim();
            }
            if (friendlyMessage.isEmpty()) {
                friendlyMessage = message.substring(message.lastIndexOf("SQLERRM:") + 8).trim();
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }

    }


    private String getErrorMessageSplitted(String message) {
        String[] messages = message.split(":");

        if (messages.length > 1) {
            String[] messageCode = messages[message.contains("PL/SQL") ? 2 : 1].split("\n");
            String errorMessage = messageCode[0];
            String errorCode = messageCode[1];

            return errorMessage;
        }

        return message;
    }
}
