package uz.genesis.trello.utils;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;
import uz.genesis.trello.service.auth.RoleService;

import java.lang.reflect.Method;

@Component
public class CacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {

        if (method.getName().equals("getAll")) {
            if (target instanceof RoleService) {
                return "roles";
            }
        }

        return "ROLE";
    }
}
