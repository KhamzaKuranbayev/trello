package uz.genesis.trello.utils.validators.main;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.main.TaskTag;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.main.TaskTagCreateDto;
import uz.genesis.trello.dto.main.TaskTagUpdateDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.exception.ValidationException;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

@Component
public class TaskTagValidator extends BaseCrudValidator<TaskTag, TaskTagCreateDto, TaskTagUpdateDto> {

    public TaskTagValidator(BaseUtils utils, IErrorRepository repository) {
        super(utils, repository);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(TaskTag domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(repository.getErrorMessage(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(TaskTag.class)));
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(repository.getErrorMessage(ErrorCodes.ID_REQUIRED, ""));
        } else if (utils.isEmpty(domain.getProjectTagId())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("projectTagId", TaskTag.class)));
        } else if (utils.isEmpty(domain.getTaskId())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("taskId", TaskTag.class)));
        }
    }
}
