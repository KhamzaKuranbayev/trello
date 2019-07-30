package uz.genesis.trello.dao;

import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.TextType;

public class TrelloSQLDialect extends org.hibernate.dialect.PostgreSQLDialect {

    public TrelloSQLDialect() {
        super();
        registerFunction("getcompletepercentage"
                , new StandardSQLFunction("getcompletepercentage", new IntegerType()));

        registerFunction("getemployeephotourl"
                , new StandardSQLFunction("getemployeephotourl", new TextType()));

        registerFunction("unblockuser"
                , new StandardSQLFunction("unblockuser", new BooleanType()));

        registerFunction("getuniqeauthid", new StandardSQLFunction("getuniqeauthid", new TextType()));

        registerFunction("hasrole"
                , new StandardSQLFunction("hasrole", new BooleanType()));
    }
}
