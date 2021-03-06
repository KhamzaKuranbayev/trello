package uz.genesis.trello.enums;

public interface Permissions {

    /**
     * USER
     */
    String USER_CREATE = "USER_CREATE";
    String USER_UPDATE = "USER_UPDATE";
    String USER_DELETE = "USER_DELETE";
    String USER_READ = "USER_READ";
    String USER_ATTACH_ROLE = "USER_ATTACH_ROLE";

    /**
     * ROLE
     */
    String ROLE_CREATE = "ROLE_CREATE";
    String ROLE_UPDATE = "ROLE_UPDATE";
    String ROLE_DELETE = "ROLE_DELETE";
    String ROLE_READ = "ROLE_READ";
    String ROLE_ATTACH_ROLE = "ROLE_ATTACH_ROLE";

    /**
     * PERMISSION
     */
    String PERMISSION_CREATE = "PERMISSION_CREATE";
    String PERMISSION_UPDATE = "PERMISSION_UPDATE";
    String PERMISSION_DELETE = "PERMISSION_DELETE";
    String PERMISSION_READ = "PERMISSION_READ";

    /**
     * GROUP
     */
    String GROUP_CREATE = "GROUP_CREATE";
    String GROUP_UPDATE = "GROUP_UPDATE";
    String GROUP_DELETE = "GROUP_DELETE";
    String GROUP_READ = "GROUP_READ";

    /**
     * COIN
     */
    String COIN_CREATE_AND_UPDATE = "COIN_CREATE_AND_UPDATE";
    String COIN_READ = "COIN_READ";

    /**
     * AUTH_TRY
     */
    String AUTH_TRY_CREATE = "AUTH_TRY_CREATE";
    String AUTH_TRY_READ = "AUTH_TRY_READ";

    /**
     * USER_LAST_LOGIN
     */
    String USER_LAST_LOGIN_CREATE = "USER_LAST_LOGIN_CREATE";
    String USER_LAST_LOGIN_READ = "USER_LAST_LOGIN_READ";

    /**
     * BACKGROUND
     */
    String BACKGROUND_CREATE = "BACKGROUND_CREATE";
    String BACKGROUND_UPDATE = "BACKGROUND_UPDATE";
    String BACKGROUND_DELETE = "BACKGROUND_DELETE";
    String BACKGROUND_READ = "BACKGROUND_READ";

    /**
     * EMPLOYEE
     */
    String EMPLOYEE_CREATE = "EMPLOYEE_CREATE";
    String EMPLOYEE_UPDATE = "EMPLOYEE_UPDATE";
    String EMPLOYEE_DELETE = "EMPLOYEE_DELETE";
    String EMPLOYEE_READ = "EMPLOYEE_READ";
    String EMPLOYEE_ALL_WITH_PHOTO = "EMPLOYEE_ALL_WITH_PHOTO";

    /**
     * FILE_STORAGE
     */
    String FILE_STORAGE_CREATE = "FILE_STORAGE_CREATE";
    String FILE_STORAGE_READ = "FILE_STORAGE_READ";

    /**
     * PROJECT_COLUMN
     */
    String PROJECT_COLUMN_CREATE = "PROJECT_COLUMN_CREATE";
    String PROJECT_COLUMN_UPDATE = "PROJECT_COLUMN_UPDATE";
    String PROJECT_COLUMN_DELETE = "PROJECT_COLUMN_DELETE";
    String PROJECT_COLUMN_READ = "PROJECT_COLUMN_READ";
    String PROJECT_COLUMN_READ_DETAILS = "PROJECT_COLUMN_READ_DETAILS";

    /**
     * PROJECT_MEMBER
     */
    String PROJECT_MEMBER_CREATE = "PROJECT_MEMBER_CREATE";
    String PROJECT_MEMBER_READ = "PROJECT_MEMBER_READ";
    String PROJECT_MEMBER_READ_EMPLOYEE = "PROJECT_MEMBER_READ";

    /**
     * PROJECT
     */
    String PROJECT_CREATE = "PROJECT_CREATE";
    String PROJECT_UPDATE = "PROJECT_UPDATE";
    String PROJECT_DELETE = "PROJECT_DELETE";
    String PROJECT_READ = "PROJECT_READ";
    String PROJECT_READ_DETAILS = "PROJECT_READ_DETAILS";
    String PROJECT_CHANGE_PHOTO = "PROJECT_CHANGE_PHOTO";

    /**
     * PROJECT_TAG
     */
    String PROJECT_TAG_CREATE = "PROJECT_TAG_CREATE";
    String PROJECT_TAG_UPDATE = "PROJECT_TAG_UPDATE";
    String PROJECT_TAG_DELETE = "PROJECT_TAG_DELETE";
    String PROJECT_TAG_READ = "PROJECT_TAG_READ";

    /**
     * TASK_ACTION
     */
    String TASK_ACTION_READ = "TASK_ACTION_READ";

    /**
     * TASK_CHECK
     */
    String TASK_CHECK_CREATE = "TASK_CHECK_CREATE";
    String TASK_CHECK_UPDATE = "TASK_CHECK_UPDATE";
    String TASK_CHECK_DELETE = "TASK_CHECK_DELETE";
    String TASK_CHECK_READ = "TASK_CHECK_READ";

    /**
     * TASK_COMMENT
     */
    String TASK_COMMENT_CREATE = "TASK_COMMENT_CREATE";
    String TASK_COMMENT_UPDATE = "TASK_COMMENT_UPDATE";
    String TASK_COMMENT_DELETE = "TASK_COMMENT_DELETE";
    String TASK_COMMENT_READ = "TASK_COMMENT_READ";

    /**
     * TASK_MEMBER
     */
    String TASK_MEMBER_CREATE = "TASK_MEMBER_CREATE";
    String TASK_MEMBER_DELETE = "TASK_MEMBER_DELETE";
    String TASK_MEMBER_READ = "TASK_MEMBER_READ";

    /**
     * TASK
     */
    String TASK_CREATE = "TASK_CREATE";
    String TASK_UPDATE = "TASK_UPDATE";
    String TASK_DELETE = "TASK_DELETE";
    String TASK_READ = "TASK_READ";
    String TASK_CREATE_TIME = "TASK_CREATE_TIME";
    String TASK_READ_DETAILS = "TASK_READ_DETAILS";
    String TASK_MOVE = "TASK_MOVE";

    /**
     * TASK_TAG
     */
    String TASK_TAG_CREATE = "TASK_TAG_CREATE";
    String TASK_TAG_UPDATE = "TASK_TAG_UPDATE";
    String TASK_TAG_DELETE = "TASK_TAG_DELETE";
    String TASK_TAG_READ = "TASK_TAG_READ";

    /**
     * ERROR_MESSAGE
     */
    String ERROR_MESSAGE_CREATE = "ERROR_MESSAGE_CREATE";
    String ERROR_MESSAGE_UPDATE = "ERROR_MESSAGE_UPDATE";
    String ERROR_MESSAGE_DELETE = "ERROR_MESSAGE_DELETE";
    String ERROR_MESSAGE_READ = "ERROR_MESSAGE_READ";

    /**
     * LANGUAGE
     */
    String LANGUAGE_READ = "LANGUAGE_READ";

    /**
     * CERTIFICATE
     */
    String CERTIFICATE_UPDATE = "CERTIFICATE_UPDATE";

    /**
     * TYPE
     */
    String TYPE_CREATE = "TYPE_CREATE";
    String TYPE_UPDATE = "TYPE_UPDATE";
    String TYPE_DELETE = "TYPE_DELETE";
    String TYPE_READ = "TYPE_READ";
    String SUB_TYPE_CREATE = "SUB_TYPE_CREATE";
}
