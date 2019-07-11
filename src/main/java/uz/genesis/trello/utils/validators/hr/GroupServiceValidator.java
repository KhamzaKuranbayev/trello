package uz.genesis.trello.utils.validators.hr;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.domain.hr.Group;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.hr.GroupCreateDto;
import uz.genesis.trello.dto.hr.GroupUpdateDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.exception.ValidationException;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

import static uz.genesis.trello.enums.ErrorCodes.ID_REQUIRED;

@Component
public class GroupServiceValidator extends BaseCrudValidator<Group, GroupCreateDto, GroupUpdateDto> {

    public GroupServiceValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto domain) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(String.format(ErrorCodes.OBJECT_IS_NULL.example, utils.toErrorParams(Group.class))/*repository.getError(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(User.class))*/);
        }
    }

    @Override
    public void baseValidation(Group domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(String.format(ErrorCodes.OBJECT_IS_NULL.example, utils.toErrorParams(Group.class))/*repository.getError(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(User.class))*/);
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(ID_REQUIRED.example/*repository.getError(ID_REQUIRED)*/);
        } else if (utils.isEmpty(domain.getName())) {
            throw new ValidationException("name is required");
        }
    }
}
