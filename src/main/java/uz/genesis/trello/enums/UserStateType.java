package uz.genesis.trello.enums;

/**
 * Created by 'Sherkhan' on 26/07/19.
 */

public enum UserStateType {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    BLOCKED("BLOCKED");

    public String code;

    UserStateType(String code) {
        this.code = code;
    }
}
