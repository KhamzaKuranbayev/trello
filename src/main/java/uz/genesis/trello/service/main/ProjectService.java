package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.ProjectCriteria;
import uz.genesis.trello.domain.main.Project;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.ProjectCreateDto;
import uz.genesis.trello.dto.main.ProjectDto;
import uz.genesis.trello.dto.main.ProjectUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.ProjectMapper;
import uz.genesis.trello.repository.main.IProjectRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.ProjectServiceValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class ProjectService extends AbstractCrudService<ProjectDto, ProjectCreateDto, ProjectUpdateDto, ProjectCriteria, IProjectRepository> implements IProjectService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final ProjectServiceValidator validator;
    private final ProjectMapper mapper;

    public ProjectService(IProjectRepository repository, BaseUtils utils, GenericMapper genericMapper, ProjectServiceValidator validator, ProjectMapper mapper) {
        super(repository, utils);
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.mapper = mapper;
    }


    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull ProjectCreateDto dto) {

        Project project = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(project);
        project.setId(repository.create(dto, "createProject"));
        if (utils.isEmpty(project.getId())) {
            logger.error(String.format("Non ProjectCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non ProjectCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(project)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<ProjectDto>> get(Long id) {
        Project project = repository.find(ProjectCriteria.childBuilder().selfId(id).build());
        if (utils.isEmpty(project)) {
            logger.error(String.format("project with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("project with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(project)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<ProjectDto>> update(@NotNull ProjectUpdateDto dto) {

        validator.validateOnUpdate(dto);
        if (repository.update(dto, "updateProject")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(String.format("could not update project with id '%s'", dto.getId()));
        }
    }


    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteProject")), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<ProjectDto>>> getAll(ProjectCriteria criteria) {
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria))), HttpStatus.OK);
    }
}
