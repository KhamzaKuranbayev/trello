package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.TaskCommentCriteria;
import uz.genesis.trello.domain.main.TaskComment;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.TaskCommentCreateDto;
import uz.genesis.trello.dto.main.TaskCommentDto;
import uz.genesis.trello.dto.main.TaskCommentUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.TaskCommentMapper;
import uz.genesis.trello.repository.main.ITaskCommentRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.TaskCommentValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class TaskCommentService extends AbstractCrudService<TaskCommentDto, TaskCommentCreateDto, TaskCommentUpdateDto, TaskCommentCriteria, ITaskCommentRepository> implements ITaskCommentService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final TaskCommentValidator validator;
    private final TaskCommentMapper mapper;
    @Autowired
    public TaskCommentService(ITaskCommentRepository repository, BaseUtils utils, GenericMapper genericMapper, TaskCommentValidator validator, TaskCommentMapper mapper) {
        super(repository, utils);
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull TaskCommentCreateDto dto) {

        TaskComment taskComment = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(taskComment);
        taskComment.setId(repository.create(dto, "createTaskComment"));
        if (utils.isEmpty(taskComment.getId())) {
            logger.error(String.format("Non TaskCommentCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non TaskCommentCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(taskComment)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<TaskCommentDto>> update(@NotNull TaskCommentUpdateDto dto) {
        validator.validateOnUpdate(dto);

        if (repository.update(dto, "updateTaskComment")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(String.format("could not update task with  id '%s'", dto.getId()));
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        if (repository.delete(id, "deleteTaskComment")) {
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } else throw new RuntimeException((String.format("could not delete taskComment with user id '%s'", id)));
    }

    @Override
    public ResponseEntity<DataDto<TaskCommentDto>> get(Long id) {
        TaskComment taskComment = repository.find(TaskCommentCriteria.childBuilder().selfId(id).build());
        if (utils.isEmpty(taskComment)) {
            logger.error(String.format("taskComment with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("taskComment with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(taskComment)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<TaskCommentDto>>> getAll(TaskCommentCriteria criteria) {
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria))), HttpStatus.OK);
    }
}
