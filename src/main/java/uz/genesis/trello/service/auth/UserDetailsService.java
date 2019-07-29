package uz.genesis.trello.service.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.auth.UserCriteria;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.dto.auth.CustomUserDetails;
import uz.genesis.trello.repository.auth.IUserRepository;

/**
 * Created by 'javokhir' on 11/06/2019
 */

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {


    /**
     * Common logger for use in subclasses.
     */
    protected final Log logger = LogFactory.getLog(getClass());

    private IUserRepository repository;

    @Autowired
    public UserDetailsService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
//    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).USER_READ)")
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = findUser(userName);
        if (user != null) {
            return new CustomUserDetails(user);
        } else {
            throw new RuntimeException(String.format("user with user name '%s' not found", userName));
        }
    }

    public User findByUserName(String userName) {
        User user = findUser(userName);
        if (user != null) {
            return user;
        } else {
            throw new RuntimeException(String.format("user with user name '%s' not found", userName));
        }
    }

    private User findUser(String userName) {
        User user;
        try {
            user = repository.find(UserCriteria.childBuilder().userName(userName).build());
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            throw new RuntimeException(String.format("user with user name '%s' not found", userName));
        }

        return user;
    }
}
