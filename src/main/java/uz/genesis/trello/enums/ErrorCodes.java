package uz.genesis.trello.enums;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 10/5/18.
 */

public enum ErrorCodes {

    ERROR_MESSAGE_NOT_FOUND("ERROR_MESSAGE_NOT_FOUND", "Error message with code ~ not found"),
    USER_NOT_FOUND_AUTH("USER_NOT_FOUND_AUTH_EMAIL", "User with provided ~ not found"),
    USER_NOT_FOUND_ID("USER_NOT_FOUND_ID", "User with provided id: ~ not found"),
    OBJECT_IS_NULL("OBJECT_IS_NULL", "Provided object: '%s' is null"),
    INVALID_FILE_PATH("OBJECT_IS_NULL", "Sorry! Filename contains invalid path sequence ~"),
    OBJECT_NOT_FOUND_ID("OBJECT_NOT_FOUND_ID", "~ with provided id: ~ not found"),
    OBJECT_NOT_FOUND("OBJECT_NOT_FOUND_ID", "~ not found"),
    OBJECT_ID_REQUIRED("OBJECT_ID_REQUIRED", "~ id not provided"),
    END_BEFORE_BEGIN_ON_DATE("END_BEFORE_BEGIN_ON_DATE", "The beginning of the selected Date should not be more than the end of the selected Date"),
    ID_REQUIRED("ID_REQUIRED", "Object id not provided");

    public String code;
    public String example;

    ErrorCodes(String code, String example) {
        this.code = code;
        this.example = example;
    }
}
