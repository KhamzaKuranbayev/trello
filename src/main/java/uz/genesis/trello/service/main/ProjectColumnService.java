package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.ProjectColumnCriteria;
import uz.genesis.trello.domain.main.ProjectColumn;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.ProjectColumnCreateDto;
import uz.genesis.trello.dto.main.ProjectColumnDetailDto;
import uz.genesis.trello.dto.main.ProjectColumnDto;
import uz.genesis.trello.dto.main.ProjectColumnUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.ProjectColumnMapper;
import uz.genesis.trello.repository.main.IProjectColumnRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.ProjectColumnServiceValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"projectColumns"})
public class ProjectColumnService extends AbstractCrudService<ProjectColumnDto, ProjectColumnCreateDto, ProjectColumnUpdateDto, ProjectColumnCriteria, IProjectColumnRepository> implements IProjectColumnService {
    protected final Log logger = LogFactory.getLog(getClass());

    private final ProjectColumnMapper mapper;
    private final GenericMapper genericMapper;
    private final ITaskService taskService;
    private final ProjectColumnServiceValidator validator;

    @Autowired
    public ProjectColumnService(IProjectColumnRepository repository, BaseUtils utils, IErrorRepository errorRepository, ProjectColumnMapper mapper, GenericMapper genericMapper, ITaskService taskService, ProjectColumnServiceValidator validator) {
        super(repository, utils, errorRepository);
        this.mapper = mapper;
        this.genericMapper = genericMapper;
        this.taskService = taskService;
        this.validator = validator;
    }

    @Override
    @CacheEvict(allEntries = true)
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull ProjectColumnCreateDto dto) {
        ProjectColumn projectColumn = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(projectColumn);
        projectColumn.setId(repository.create(dto, "createProjectColumn"));
        if (utils.isEmpty(projectColumn.getId())) {
            logger.error(String.format("Non ProjectColumnCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(ProjectColumn.class)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(projectColumn)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<ProjectColumnDto>> get(Long id) {
        ProjectColumn projectColumn = repository.find(ProjectColumnCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(projectColumn)) {
            logger.error(String.format("projectColumn with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(ProjectColumn.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(projectColumn)), HttpStatus.OK);
    }

    @Override
    @CacheEvict(allEntries = true)
    public ResponseEntity<DataDto<ProjectColumnDto>> update(@NotNull ProjectColumnUpdateDto dto) {

        validator.validateOnUpdate(dto);
        if (repository.update(dto, "updateProjectColumn")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_UPDATED, utils.toErrorParams(ProjectColumn.class, dto.getId())));
        }
    }

    @Override
    @CacheEvict(allEntries = true)
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteProjectColumn")), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<ProjectColumnDto>>> getAll(ProjectColumnCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }


    @Override
    @Cacheable(key = "#root.methodName + #criteria.projectId")
    public List<ProjectColumnDetailDto> getAllColumns(ProjectColumnCriteria criteria) {
        return mapper.toDetailDto(repository.findAll(criteria));
    }


}
