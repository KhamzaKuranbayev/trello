package uz.genesis.trello.enums;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 10/5/18.
 */

public enum Headers {

    LANGUAGE("language", "ru");

    public String key;
    public String defValue;

    Headers(String key, String defValue) {
        this.key = key;
        this.defValue = defValue;
    }

    public static Headers findByKey(String key) {
        for (Headers param : values()) {
            if (param.key.equals(key)) {
                return param;
            }
        }
        return null;
    }
}
