package uz.genesis.trello.utils.validators.auth;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.AuthTry;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.auth.AuthTryCreateDto;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

@Component
public class AuthTryValidator extends BaseCrudValidator<AuthTry, AuthTryCreateDto, CrudDto>{
    public AuthTryValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(AuthTry domain, boolean idRequired) {

    }
}
