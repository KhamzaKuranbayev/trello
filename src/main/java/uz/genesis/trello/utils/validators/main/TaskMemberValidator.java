package uz.genesis.trello.utils.validators.main;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.main.TaskMember;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.main.TaskMemberCreateDto;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

@Component
public class TaskMemberValidator extends BaseCrudValidator<TaskMember, TaskMemberCreateDto, CrudDto> {
    public TaskMemberValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(TaskMember domain, boolean idRequired) {

    }
}
