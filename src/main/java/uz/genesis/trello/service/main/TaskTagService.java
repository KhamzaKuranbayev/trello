package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.TaskTagCriteria;
import uz.genesis.trello.domain.main.TaskTag;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.TaskTagCreateDto;
import uz.genesis.trello.dto.main.TaskTagDto;
import uz.genesis.trello.dto.main.TaskTagUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.TaskTagMapper;
import uz.genesis.trello.repository.main.ITaskTagRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.TaskTagValidator;

import javax.validation.constraints.NotNull;
import java.util.List;


@Service
@CacheConfig(cacheNames = {"taskTags"})
public class TaskTagService extends AbstractCrudService<TaskTagDto, TaskTagCreateDto, TaskTagUpdateDto, TaskTagCriteria, ITaskTagRepository> implements ITaskTagService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final TaskTagValidator validator;
    private final TaskTagMapper mapper;

    public TaskTagService(ITaskTagRepository repository, BaseUtils utils, IErrorRepository errorRepository, GenericMapper genericMapper, TaskTagValidator validator, TaskTagMapper mapper) {
        super(repository, utils, errorRepository);
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    @CacheEvict(allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_TAG_CREATE)")
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull TaskTagCreateDto dto) {

        TaskTag tasktag = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(tasktag);
        tasktag.setId(repository.create(dto, "createProjectTaskTag"));
        if (utils.isEmpty(tasktag.getId())) {
            logger.error(String.format("Non TaskTagCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(TaskTag.class)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(tasktag)), HttpStatus.CREATED);
    }

    @Override
    @CacheEvict(allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_TAG_UPDATE)")
    public ResponseEntity<DataDto<TaskTagDto>> update(@NotNull TaskTagUpdateDto dto) {
        validator.validateDomainOnUpdate(mapper.fromUpdateDto(dto));

        if (repository.update(dto, "updateProjectTaskTag")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_UPDATED, utils.toErrorParams(TaskTag.class, dto.getId())));
        }
    }

    @Override
    @CacheEvict(allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_TAG_DELETE)")
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_TAG_READ)")
    public ResponseEntity<DataDto<TaskTagDto>> get(Long id) {
        TaskTag tasktag = repository.find(TaskTagCriteria.childBuilder().selfId(id).build());
        if (utils.isEmpty(tasktag)) {
            logger.error(String.format("taskTag with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(TaskTag.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(tasktag)), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_TAG_READ)")
    public ResponseEntity<DataDto<List<TaskTagDto>>> getAll(TaskTagCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }

    @Override
    @Cacheable(key = "#root.methodName + #criteria.taskId")
    public List<TaskTagDto> getAllTaskTagList(TaskTagCriteria criteria) {
        return mapper.toDto(repository.findAll(criteria));
    }
}
