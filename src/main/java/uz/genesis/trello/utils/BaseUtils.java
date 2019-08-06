package uz.genesis.trello.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.genesis.trello.dao.FunctionParam;
import uz.genesis.trello.domain.Auditable;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * Created by 'javokhir' on 10/06/2019
 */

@Component
public class BaseUtils {

    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"};
    @Autowired
    private ObjectMapper objectMapper;

    private static String toHex(byte[] data) {
        char[] chars = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            chars[i * 2] = HEX_DIGITS[(data[i] >> 4) & 0xf];
            chars[i * 2 + 1] = HEX_DIGITS[data[i] & 0xf];
        }
        return new String(chars);
    }

    public static String defineMacAddress() {
        InetAddress ip;
        StringBuilder sb = new StringBuilder();
        try {

            ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostAddress());

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            System.out.print("Current MAC address : ");


            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            System.out.println(sb.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return sb.toString();
    }

    public String getClientIpAddress(HttpServletRequest request) {
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    public boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public boolean isEmpty(List<?> items) {
        return items == null || items.isEmpty();
    }

    public boolean isEmpty(Object l) {
        return l == null;
    }

    public String toErrorParams(Object... args) {
        StringBuilder builder = new StringBuilder();
        Arrays.asList(args).forEach(t -> builder.append("#").append(toStringErrorParam(t)));
        return builder.toString().substring(1);
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

    public String encodeToBase64(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    public String generateParamText(List<FunctionParam> params) {
        StringBuilder builder = new StringBuilder();
        builder.append(" ( ");
        for (int i = 0; i < params.size(); i++) {
            if (i == 0) {
                builder.append(" ? ");
            } else {
                builder.append(" ,? ");
            }
        }
        builder.append(" ) ");
        return builder.toString();
    }

    public JsonNode fromStringToNode(String data) {
        try {
            return objectMapper.readTree(data);
        } catch (IOException e) {
            throw new RuntimeException("could not parse string data");
        }
    }


}
