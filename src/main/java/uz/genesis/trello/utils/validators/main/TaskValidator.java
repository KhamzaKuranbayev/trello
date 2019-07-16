package uz.genesis.trello.utils.validators.main;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.main.Task;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.main.TaskCreateDto;
import uz.genesis.trello.dto.main.TaskUpdateDto;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;
@Component
public class TaskValidator extends BaseCrudValidator<Task, TaskCreateDto, TaskUpdateDto> {
    public TaskValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(Task domain, boolean idRequired) {

    }
}
