package uz.genesis.trello.utils.validators.main;

import uz.genesis.trello.domain.main.ProjectColumn;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.main.ProjectColumnCreateDto;
import uz.genesis.trello.dto.main.ProjectColumnUpdateDto;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

public class ProjectColumnServiceValidator extends BaseCrudValidator<ProjectColumn, ProjectColumnCreateDto, ProjectColumnUpdateDto> {
    public ProjectColumnServiceValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto domain) {

    }

    @Override
    public void baseValidation(ProjectColumn domain, boolean idRequired) {

    }
}
