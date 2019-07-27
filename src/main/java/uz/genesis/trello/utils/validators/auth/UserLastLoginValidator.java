package uz.genesis.trello.utils.validators.auth;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.UserLastLogin;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.auth.UserLastLoginCreateDto;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

@Component
public class UserLastLoginValidator extends BaseCrudValidator<UserLastLogin, UserLastLoginCreateDto, CrudDto>  {
    public UserLastLoginValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(UserLastLogin domain, boolean idRequired) {

    }
}
