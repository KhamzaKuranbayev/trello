package uz.genesis.trello.utils.validators.hr;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.files.ResourceFile;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.hr.EmployeeCreateDto;
import uz.genesis.trello.dto.hr.EmployeeUpdateDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.exception.ValidationException;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

import static uz.genesis.trello.enums.ErrorCodes.ID_REQUIRED;

/**
 * Created by 'javokhir' on 04/07/2019
 */

@Component
public class EmployeeServiceValidator extends BaseCrudValidator<Employee, EmployeeCreateDto, EmployeeUpdateDto> {

    public EmployeeServiceValidator(BaseUtils utils, IErrorRepository repository) {
        super(utils, repository);
    }

    @Override
    public void baseValidation(CrudDto domain) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(String.format(ErrorCodes.OBJECT_IS_NULL.example, utils.toErrorParams(Employee.class))/*repository.getError(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(User.class))*/);
        }
        if (domain instanceof EmployeeCreateDto) {

        } else {
            EmployeeUpdateDto dto = (EmployeeUpdateDto) domain;
            if (utils.isEmpty(dto.getUserId())) {
                throw new ValidationException(String.format(ID_REQUIRED.example, utils.toErrorParams(Employee.class)));
            }
        }
    }

    @Override
    public void baseValidation(Employee domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(repository.getErrorMessage(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(ResourceFile.class)));
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(repository.getErrorMessage(ErrorCodes.ID_REQUIRED, ""));
        } else if (utils.isEmpty(domain.getUserId())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("userId", ResourceFile.class)));
        }
    }
}
