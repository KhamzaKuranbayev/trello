package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.TaskCriteria;
import uz.genesis.trello.domain.main.Task;
import uz.genesis.trello.domain.main.TaskTimeEntry;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.*;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.TaskMapper;
import uz.genesis.trello.mapper.main.TaskTimeEntryMapper;
import uz.genesis.trello.repository.main.ITaskRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.TaskValidator;

import javax.validation.constraints.NotNull;
import java.sql.Types;
import java.util.List;

@Service
public class TaskService extends AbstractCrudService<TaskDto, TaskCreateDto, TaskUpdateDto, TaskCriteria, ITaskRepository> implements ITaskService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final TaskTimeEntryMapper taskTimeEntryMapper;
    private final TaskValidator validator;
    private final TaskMapper mapper;

    @Autowired
    public TaskService(ITaskRepository repository, BaseUtils utils, GenericMapper genericMapper, TaskTimeEntryMapper taskTimeEntryMapper, TaskValidator validator, TaskMapper mapper) {
        super(repository, utils);
        this.genericMapper = genericMapper;
        this.taskTimeEntryMapper = taskTimeEntryMapper;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull TaskCreateDto dto) {

        Task task = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(task);
        task.setId(repository.create(dto, "createTask"));
        if (utils.isEmpty(task.getId())) {
            logger.error(String.format("Non TaskCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non TaskCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(task)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<TaskDto>> update(@NotNull TaskUpdateDto dto) {
        validator.validateOnUpdate(dto);

        if (repository.update(dto, "updateTask")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(String.format("could not update task with  id '%s'", dto.getId()));
        }
    }



    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        if (repository.delete(id, "deleteTask")) {
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } else throw new RuntimeException((String.format("could not delete task with id '%s'", id)));
    }

    @Override
    public ResponseEntity<DataDto<TaskDto>> get(Long id) {
        Task task = repository.find(TaskCriteria.childBuilder().selfId(id).build());
        if (utils.isEmpty(task)) {
            logger.error(String.format("task with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("task with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(task)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<TaskDto>> move(MovingTaskDto dto) {
        if(repository.call(dto, "moveTask", Types.BOOLEAN)){
            return get(dto.getId());
        } else {
            throw new RuntimeException((String.format("could not move task with id '%s'", dto.getId())));
        }

    }

    @Override
    public ResponseEntity<DataDto<List<TaskDto>>> getAll(TaskCriteria criteria) {
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria))), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> createTaskTimeEntry(TaskTimeEntryCreateDto taskTimeEntryCreateDto) {
        TaskTimeEntry taskTimeEntry = taskTimeEntryMapper.fromCreateDto(taskTimeEntryCreateDto);
        taskTimeEntry.setId(repository.call(taskTimeEntryCreateDto, "createTaskTimeEntry", Types.BIGINT));
        if (utils.isEmpty(taskTimeEntry.getId())) {
            logger.error(String.format("Non TaskTimeEntryCreateDto defined '%s' ", new Gson().toJson(taskTimeEntryCreateDto)));
            throw new RuntimeException(String.format("Non TaskTimeEntryCreateDto defined '%s' ", new Gson().toJson(taskTimeEntryCreateDto)));
        }
        return  new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(taskTimeEntry)), HttpStatus.CREATED);
    }
}
