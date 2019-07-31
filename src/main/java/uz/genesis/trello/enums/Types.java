package uz.genesis.trello.enums;

public enum Types {

    DEFAULT("default", "default"),
    PROJECT_TYPE(null, "PROJECT_TYPE"),
    PROJECT_TYPE_KANBAN("PROJECT_TYPE", "PROJECT_TYPE_KANBAN"),
    PROJECT_TYPE_TRELLO("PROJECT_TYPE", "PROJECT_TYPE_TRELLO");

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
