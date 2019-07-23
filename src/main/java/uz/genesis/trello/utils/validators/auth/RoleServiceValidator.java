package uz.genesis.trello.utils.validators.auth;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.auth.Role;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.auth.AttachPermissionDto;
import uz.genesis.trello.dto.auth.RoleCreateDto;
import uz.genesis.trello.dto.auth.RoleUpdateDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.exception.ValidationException;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

import static uz.genesis.trello.enums.ErrorCodes.ID_REQUIRED;

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
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(String.format(ErrorCodes.OBJECT_IS_NULL.example, utils.toErrorParams(Role.class)));
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(ID_REQUIRED.example);
        } else if (utils.isEmpty(domain.getCodeName())) {
            throw new ValidationException("code name is required");
        }
        if (utils.isEmpty(domain.getRoleName())) {
            throw new ValidationException("codename is required");
        }
    }


    public void validateOnAttach(AttachPermissionDto attachRoleDto){
        if(utils.isEmpty(attachRoleDto.getId())){
            throw new ValidationException("id is required");
        }
        if(utils.isEmpty(attachRoleDto.getPermissions())){
            throw new ValidationException("permissions must not be null");
        }
    }
}
