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
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

@Component
public class RoleServiceValidator extends BaseCrudValidator<Role, RoleCreateDto, RoleUpdateDto> {
    public RoleServiceValidator(BaseUtils utils, IErrorRepository repository) {
        super(utils, repository);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(Role domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(repository.getErrorMessage(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(Role.class)));
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(repository.getErrorMessage(ErrorCodes.ID_REQUIRED, ""));
        } else if (utils.isEmpty(domain.getCodeName())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("codeName", Role.class)));
        } else if (utils.isEmpty(domain.getRoleName())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("roleName", Role.class)));
        } else if (utils.isEmpty(domain.getOrganizationId())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("organizationId", Role.class)));
        }
    }


    public void validateOnAttach(AttachPermissionDto attachRoleDto) {
        if (utils.isEmpty(attachRoleDto.getId())) {
            throw new IdRequiredException(repository.getErrorMessage(ErrorCodes.ID_REQUIRED, ""));
        }
        if (utils.isEmpty(attachRoleDto.getPermissions())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("organizationId", Role.class)));
        }
    }
}
