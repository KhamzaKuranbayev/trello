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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.TaskCommentCriteria;
import uz.genesis.trello.domain.main.TaskComment;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.TaskCommentCreateDto;
import uz.genesis.trello.dto.main.TaskCommentDto;
import uz.genesis.trello.dto.main.TaskCommentUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.TaskCommentMapper;
import uz.genesis.trello.repository.main.ITaskCommentRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.TaskCommentValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"comments"})
public class TaskCommentService extends AbstractCrudService<TaskCommentDto, TaskCommentCreateDto, TaskCommentUpdateDto, TaskCommentCriteria, ITaskCommentRepository> implements ITaskCommentService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final TaskCommentValidator validator;
    private final TaskCommentMapper mapper;

    @Autowired
    public TaskCommentService(ITaskCommentRepository repository, BaseUtils utils, IErrorRepository errorRepository, GenericMapper genericMapper, TaskCommentValidator validator, TaskCommentMapper mapper) {
        super(repository, utils, errorRepository);
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    @CacheEvict(allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_COMMENT_CREATE)")
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull TaskCommentCreateDto dto) {

        TaskComment taskComment = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(taskComment);
        taskComment.setId(repository.create(dto, "createTaskComment"));
        if (utils.isEmpty(taskComment.getId())) {
            logger.error(String.format("Non TaskCommentCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(TaskComment.class)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(taskComment)), HttpStatus.CREATED);
    }

    @Override
    @CacheEvict(allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_COMMENT_UPDATE)")
    public ResponseEntity<DataDto<TaskCommentDto>> update(@NotNull TaskCommentUpdateDto dto) {
        validator.validateDomainOnUpdate(mapper.fromUpdateDto(dto));

        if (repository.update(dto, "updateTaskComment")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_UPDATED, utils.toErrorParams(TaskComment.class, dto.getId())));
        }
    }

    @Override
    @CacheEvict(allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_COMMENT_DELETE)")
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_COMMENT_READ)")
    public ResponseEntity<DataDto<TaskCommentDto>> get(Long id) {
        TaskComment taskComment = repository.find(TaskCommentCriteria.childBuilder().selfId(id).build());
        if (utils.isEmpty(taskComment)) {
            logger.error(String.format("taskComment with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(TaskComment.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(taskComment)), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_COMMENT_READ)")
    public ResponseEntity<DataDto<List<TaskCommentDto>>> getAll(TaskCommentCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }

    @Override
    @Cacheable(key = "#root.methodName + #criteria.taskId")
    public Long getCommentCount(TaskCommentCriteria criteria) {
        return repository.getTotalCount(criteria);
    }

    @Override
    @Cacheable(key = "#root.methodName + #criteria.taskId")
    public List<TaskCommentDto> getAllTaskCommentList(TaskCommentCriteria criteria){
        return mapper.toDto(repository.findAll(criteria));
    }
}
