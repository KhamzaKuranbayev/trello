package uz.genesis.trello.utils.validators.main;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.main.Project;
import uz.genesis.trello.domain.main.ProjectMember;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.main.ProjectCreateDto;
import uz.genesis.trello.dto.main.ProjectUpdateDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.exception.ValidationException;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

import static uz.genesis.trello.enums.ErrorCodes.ID_REQUIRED;
@Component
public class ProjectServiceValidator extends BaseCrudValidator<Project, ProjectCreateDto, ProjectUpdateDto> {
    public ProjectServiceValidator(BaseUtils utils) {
        super(utils);
    }

    @Override
    public void baseValidation(CrudDto domain) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(String.format(ErrorCodes.OBJECT_IS_NULL.example, utils.toErrorParams(Project.class))/*repository.getError(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(User.class))*/);
        }
        if (domain instanceof Project) {

        } else {
            ProjectUpdateDto dto = (ProjectUpdateDto) domain;
            if (utils.isEmpty(dto.getId())) {
                throw new ValidationException(String.format(ID_REQUIRED.example, utils.toErrorParams(Project.class)));
            }
        }

    }

    @Override
    public void baseValidation(Project domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(String.format(ErrorCodes.OBJECT_IS_NULL.example, utils.toErrorParams(ProjectMember.class)));
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(ID_REQUIRED.example);
        } else if (utils.isEmpty(domain.getProjectType())) {
            throw new ValidationException("type is required");
        }
    }
}
