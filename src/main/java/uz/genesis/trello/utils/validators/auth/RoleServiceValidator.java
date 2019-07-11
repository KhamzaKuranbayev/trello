package uz.genesis.trello.utils.validators.auth;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.Role;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.auth.RoleCreateDto;
import uz.genesis.trello.dto.auth.RoleUpdateDto;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;
@Component
public class RoleServiceValidator  extends BaseCrudValidator<Role, RoleCreateDto, RoleUpdateDto> {
    public RoleServiceValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(Role domain, boolean idRequired) {

    }
}
