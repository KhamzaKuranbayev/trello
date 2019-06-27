package uz.genesis.trello.utils;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.Auditable;

import java.util.Arrays;

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

    public String toErrorParams(Object... args) {
        StringBuilder builder = new StringBuilder();
        Arrays.asList(args).forEach(t -> builder.append("|").append(toStringErrorParam(t)));
        return builder.toString();
    }

    private String toStringErrorParam(Object argument) {

        if (argument instanceof Auditable) {
            return argument.getClass().getSimpleName();
        }

        return argument.toString();
    }
}
