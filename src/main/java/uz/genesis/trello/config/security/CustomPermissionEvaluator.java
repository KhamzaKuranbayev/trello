package uz.genesis.trello.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.Permission;
import uz.genesis.trello.domain.auth.Role;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.repository.auth.IUserSessionRepository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 10/5/18.
 */

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private IUserSessionRepository repository;



    @Override
    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
        if ((auth == null) || /*(targetDomainObject == null) ||*/ !(permission instanceof String)) {
            return false;
        }
//        final String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();
        return hasPrivilege(auth, /*targetType,*/ permission.toString().toUpperCase());
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(auth, /*targetType.toUpperCase(),*/ permission.toString().toUpperCase());
    }

    private boolean hasPrivilege(Authentication auth, /*String targetType,*/ String permission) {
        User user = new User(); repository.findByUserName(auth.getName());
        List<Role> roles = new ArrayList<>(user.getRoles());
        List<Permission> permissions = new ArrayList<>();

        for (Role role : roles) {
            permissions.addAll(role.getPermissions());
        }

        roles.forEach(t -> permissions.addAll(t.getPermissions()));

//        for (Permission grantedAuth : permissions) {
//            if (grantedAuth.getName().contains(permission)) {
//                return true;
//            }
//        }
//        return false;

        return permissions.stream().anyMatch(t -> t.getName().equals(permission));
    }
}
