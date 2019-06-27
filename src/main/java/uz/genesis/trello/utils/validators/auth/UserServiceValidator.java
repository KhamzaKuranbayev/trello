package uz.genesis.trello.utils.validators.auth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.service.auth.UserService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

import static uz.genesis.trello.enums.ErrorCodes.ID_REQUIRED;

/**
 * Created by 'javokhir' on 27/06/2019
 */

@Component
public class UserServiceValidator extends BaseCrudValidator<User> {


    public UserServiceValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(User domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(String.format(ErrorCodes.OBJECT_IS_NULL.example, utils.toErrorParams(User.class))/*repository.getError(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(User.class))*/);
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(ID_REQUIRED.example/*repository.getError(ID_REQUIRED)*/);
        } else if (!isValidEmail(domain.getEmail())) {
            throw new RuntimeException(String.format(" email '%s' is not valid", domain.getEmail()));
        } else if (utils.isEmpty(domain.getUserName()) || domain.getUserName().isEmpty()) {
            throw new RuntimeException("userName is required");
        } else if (utils.isEmpty(domain.getPassword()) || domain.getPassword().isEmpty()) {
            throw new RuntimeException("password is required");
        }
    }

    static boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
