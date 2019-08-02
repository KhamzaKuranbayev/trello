package uz.genesis.trello.enums;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 10/5/18.
 */

public enum ErrorCodes {

    ERROR_MESSAGE_NOT_FOUND("ERROR_MESSAGE_NOT_FOUND", "Error message with code ~ not found"),
    USER_NOT_FOUND_AUTH("USER_NOT_FOUND_AUTH_EMAIL", "User with provided ~ not found"),
    USER_NOT_FOUND_ID("USER_NOT_FOUND_ID", "User with provided id: ~ not found"),
    OBJECT_IS_NULL("OBJECT_IS_NULL", "Provided object: '%s' is null"),
    OBJECT_GIVEN_FIELD_REQUIRED("OBJECT_FIELD_NULL", "Provided field :'%s' of '%s'  is required"),
    OBJECT_COULD_NOT_CREATED("OBJECT_COULD_NOT_CREATED", "Provided object: '%s' could not created "),
    OBJECT_COULD_NOT_UPDATED("OBJECT_COULD_NOT_UPDATED", "Could not update '%s' with id '%s'"),
    COULD_NOT_ATTACH("COULD_NOT_ATTACH", "Could not attach '%s' to '%s' with id '%s'"),
    EMAIL_NOT_VALID("EMAIL_NOT_VALID", "Email '%s' is not valid"),
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
