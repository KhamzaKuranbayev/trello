package uz.genesis.trello.enums;

/**
 * Created by 'Sherkhan' on 26/07/19.
 */

public enum UserType {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    BLOCKED("BLOCKED");

    public String code;

    UserType(String code) {
        this.code = code;
    }
}
