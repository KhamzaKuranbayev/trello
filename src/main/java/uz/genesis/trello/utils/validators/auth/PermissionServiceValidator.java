package uz.genesis.trello.utils.validators.auth;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.Permission;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.auth.PermissionCreateDto;
import uz.genesis.trello.dto.auth.PermissionUpdateDto;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

@Component
public class PermissionServiceValidator extends BaseCrudValidator<Permission, PermissionCreateDto, PermissionUpdateDto> {
    public PermissionServiceValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(Permission domain, boolean idRequired) {

    }
}
