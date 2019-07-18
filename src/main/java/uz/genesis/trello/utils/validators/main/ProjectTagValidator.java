package uz.genesis.trello.utils.validators.main;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.main.ProjectTag;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.main.ProjectTagCreateDto;
import uz.genesis.trello.dto.main.ProjectTagUpdateDto;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

@Component
public class ProjectTagValidator extends BaseCrudValidator<ProjectTag, ProjectTagCreateDto, ProjectTagUpdateDto> {
    public ProjectTagValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(ProjectTag domain, boolean idRequired) {

    }
}