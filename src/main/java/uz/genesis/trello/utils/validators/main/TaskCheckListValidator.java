package uz.genesis.trello.utils.validators.main;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.main.TaskCheckList;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.main.TaskCheckListCreateDto;
import uz.genesis.trello.dto.main.TaskCheckListUpdateDto;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

@Component
public class TaskCheckListValidator extends BaseCrudValidator<TaskCheckList, TaskCheckListCreateDto, TaskCheckListUpdateDto> {
    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(TaskCheckList domain, boolean idRequired) {

    }

    public TaskCheckListValidator(BaseUtils utils) {
        super(utils);
    }
}
