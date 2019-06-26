package uz.genesis.trello.utils;

import org.springframework.stereotype.Component;

/**
 * Created by 'javokhir' on 10/06/2019
 */

@Component
public class BaseUtils {

    public boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }
    public boolean isEmpty(Object l) {
        return l == null;
    }
}
