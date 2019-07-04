package uz.genesis.trello.utils.validators.auth;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.exception.ValidationException;
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
        } else if (utils.isEmpty(domain.getEmail())) {
            throw new ValidationException("email is required");
        }if (!isValidEmail(domain.getEmail())) {
            throw new ValidationException(String.format(" email '%s' is not valid", domain.getEmail()));
        } else if (utils.isEmpty(domain.getUserName())) {
            throw new ValidationException("userName is required");
        } else if (utils.isEmpty(domain.getPassword())) {
            throw new ValidationException("password is required");
        }
    }

    private boolean isValidEmail(String email) {

        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
