package uz.genesis.trello.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.enums.Headers;
import uz.genesis.trello.repository.auth.IUserSessionRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 10/5/18.
 */

@Component
public class UserSession {

    HttpServletRequest request;
    BaseUtils utils;
    IUserSessionRepository repository;
    User user;


    @Autowired
    public UserSession(HttpServletRequest request, BaseUtils utils, IUserSessionRepository repository) {
        this.request = request;
        this.utils = utils;
        this.repository = repository;
    }

    private void init() {
        user = repository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    public User getUser() {
        init();
        return user;
    }

    //get request headers
    public Map<Headers, String> getHeadersInfo() {

        HashMap<String, String> map = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return convertHeader(map);
    }

    public String getLanguage() {
        Map<Headers, String> headers = getHeadersInfo();
        if (headers.containsKey((Headers.LANGUAGE))) {
            return headers.get(Headers.LANGUAGE);
        } else {
            return Headers.LANGUAGE.defValue;
        }
    }

    private HashMap<Headers, String> convertHeader(HashMap<String, String> map) {
        HashMap<Headers, String> hashMap = new HashMap<>();

        for (String s : map.keySet()) {
            if (Headers.findByKey(s) != null) {
                hashMap.put(Headers.findByKey(s), map.get(s));
            }
        }

        return hashMap;
    }

}
