package uz.genesis.trello.enums;

public enum Types {

    DEFAULT("default", "default"),
    PROJECT_TYPE(null, "PROJECT_TYPE"),
    AUTH_OTP_TYPE(null, "AUTH_OTP_TYPE"),


    PROJECT_TYPE_KANBAN("PROJECT_TYPE", "KANBAN"),
    PROJECT_TYPE_TRELLO("PROJECT_TYPE", "TRELLO"),
    AUTH_OTP_TYPE_PHONE("AUTH_OTP_TYPE", "PHONE"),
    AUTH_OTP_TYPE_EMAIL("AUTH_OTP_TYPE", "EMAIL");

    public String parent;
    public String value;

    Types(String parent, String value) {
        this.parent = parent;
        this.value = value;
    }

    public static Types findByValue(String key) {
        if (key == null) return DEFAULT;
        for (Types type : values()) {
            if (type.value.equals(key)) {
                return type;
            }
        }
        return DEFAULT;
    }

}
