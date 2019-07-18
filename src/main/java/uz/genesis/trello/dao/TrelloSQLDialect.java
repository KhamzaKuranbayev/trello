package uz.genesis.trello.dao;

import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.IntegerType;

public class TrelloSQLDialect extends org.hibernate.dialect.PostgreSQLDialect {

    public TrelloSQLDialect() {
        super();
        registerFunction("getcompletepercentage"
                , new StandardSQLFunction("getcompletepercentage", new IntegerType()));
    }
}
