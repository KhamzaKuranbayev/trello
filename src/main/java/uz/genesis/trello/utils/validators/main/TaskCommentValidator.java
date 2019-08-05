package uz.genesis.trello.utils.validators.main;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.main.TaskComment;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.main.TaskCommentCreateDto;
import uz.genesis.trello.dto.main.TaskCommentUpdateDto;
import uz.genesis.trello.service.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

@Component
public class TaskCommentValidator extends BaseCrudValidator<TaskComment, TaskCommentCreateDto, TaskCommentUpdateDto> {

    public TaskCommentValidator(BaseUtils utils, IErrorRepository repository) {
        super(utils, repository);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(TaskComment domain, boolean idRequired) {

    }
}
