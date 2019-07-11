package uz.genesis.trello.utils.validators.auth;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.Permission;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.domain.hr.Group;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.auth.PermissionCreateDto;
import uz.genesis.trello.dto.auth.PermissionUpdateDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.exception.ValidationException;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

import static uz.genesis.trello.enums.ErrorCodes.ID_REQUIRED;

@Component
public class PermissionServiceValidator extends BaseCrudValidator<Permission, PermissionCreateDto, PermissionUpdateDto> {
    public PermissionServiceValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto domain) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(String.format(ErrorCodes.OBJECT_IS_NULL.example, utils.toErrorParams(Permission.class)));
        }
    }

    @Override
    public void baseValidation(Permission domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(String.format(ErrorCodes.OBJECT_IS_NULL.example, utils.toErrorParams(User.class)));
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(ID_REQUIRED.example);
        } else if (utils.isEmpty(domain.getCodeName())) {
            throw new ValidationException("code name is required");
        }
       if (utils.isEmpty(domain.getName())) {
            throw new ValidationException("name is required");
        }
    }
}
