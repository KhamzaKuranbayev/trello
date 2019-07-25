package uz.genesis.trello.utils;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.Auditable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public String encodeToMd5(String data) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes(), 0, data.length());
            return toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();

    private static String toHex(byte[] data) {
        char[] chars = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            chars[i * 2] = HEX_DIGITS[(data[i] >> 4) & 0xf];
            chars[i * 2 + 1] = HEX_DIGITS[data[i] & 0xf];
        }
        return new String(chars);
    }



}
