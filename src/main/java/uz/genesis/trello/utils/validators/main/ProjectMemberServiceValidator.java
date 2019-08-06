package uz.genesis.trello.utils.validators.main;

import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.main.ProjectMember;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.main.ProjectMemberCreateDto;
import uz.genesis.trello.dto.main.ProjectMemberUpdateDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.exception.IdRequiredException;
import uz.genesis.trello.exception.RequestObjectNullPointerException;
import uz.genesis.trello.exception.ValidationException;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.BaseCrudValidator;

import static uz.genesis.trello.enums.ErrorCodes.ID_REQUIRED;

@Component
public class ProjectMemberServiceValidator extends BaseCrudValidator<ProjectMember, ProjectMemberCreateDto, ProjectMemberUpdateDto> {

    public ProjectMemberServiceValidator(BaseUtils utils, IErrorRepository repository) {
        super(utils, repository);
    }

    @Override
    public void baseValidation(CrudDto domain) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(String.format(ErrorCodes.OBJECT_IS_NULL.example, utils.toErrorParams(ProjectMember.class)), "CrudDto"/*repository.getError(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(User.class))*/);
        }
        if (domain instanceof ProjectMember) {

        } else {
            ProjectMemberUpdateDto dto = (ProjectMemberUpdateDto) domain;
            if (utils.isEmpty(dto.getId())) {
                throw new ValidationException(repository.getErrorMessage(ID_REQUIRED.example, utils.toErrorParams(ProjectMember.class)), "id");
            }
        }
    }

    @Override
    public void baseValidation(ProjectMember domain, boolean idRequired) {
        if (utils.isEmpty(domain)) {
            throw new RequestObjectNullPointerException(repository.getErrorMessage(ErrorCodes.OBJECT_IS_NULL, utils.toErrorParams(ProjectMember.class)), "ProjectMember");
        } else if (idRequired && utils.isEmpty(domain.getId())) {
            throw new IdRequiredException(repository.getErrorMessage(ErrorCodes.ID_REQUIRED, ""), "id");
        } else if (utils.isEmpty(domain.getEmployee())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("employee", ProjectMember.class)), "employee");
        } else if (utils.isEmpty(domain.getProjectId())) {
            throw new ValidationException(repository.getErrorMessage(ErrorCodes.OBJECT_GIVEN_FIELD_REQUIRED, utils.toErrorParams("projectId", ProjectMember.class)), "projectId");
        }
    }
}
