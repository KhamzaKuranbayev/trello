package uz.genesis.trello.utils.validators.hr;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.dto.hr.EmployeeCreateDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.exception.ValidationException;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

import static uz.genesis.trello.enums.ErrorCodes.ID_REQUIRED;

/**
 * Created by 'javokhir' on 04/07/2019
 */

@Component
public class EmployeeServiceValidator extends BaseCrudValidator<Employee> {

    public EmployeeServiceValidator(BaseUtils utils) {
        super(utils);
    }

    public void validateOnCreateWithUser(EmployeeCreateDto dto) {
        if (utils.isEmpty(dto)) {
            throw new RequestObjectNullPointerException(String.format(ErrorCodes.OBJECT_IS_NULL.example, utils.toErrorParams(Employee.class))/*repository.getError(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(User.class))*/);
        }
    }

    @Override
    public void baseValidation(Employee domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(String.format(ErrorCodes.OBJECT_IS_NULL.example, utils.toErrorParams(Employee.class))/*repository.getError(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(User.class))*/);
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(ID_REQUIRED.example/*repository.getError(ID_REQUIRED)*/);
        } else if (utils.isEmpty(domain.getFirstName())) {
            throw new ValidationException("firstName is required");
        } else if (utils.isEmpty(domain.getLastName())) {
            throw new ValidationException("lasName is required");
        }
    }
}