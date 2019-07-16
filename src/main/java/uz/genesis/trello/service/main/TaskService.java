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
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.TaskCreateDto;
import uz.genesis.trello.dto.main.TaskDto;
import uz.genesis.trello.dto.main.TaskUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.TaskMapper;
import uz.genesis.trello.repository.main.ITaskRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.TaskValidator;

import javax.validation.constraints.NotNull;

@Service
public class TaskService extends AbstractCrudService<TaskDto, TaskCreateDto, TaskUpdateDto, TaskCriteria, ITaskRepository> implements ITaskService {
    @Autowired
    public TaskService(ITaskRepository repository, BaseUtils utils, GenericMapper genericMapper, TaskValidator validator, TaskMapper mapper) {
        super(repository, utils);
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.mapper = mapper;
    }

    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final TaskValidator validator;
    private final TaskMapper mapper;


    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull TaskCreateDto dto) {

        Task task = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(task);
        try{
            task.setId(repository.create(dto, "createTask"));
        }catch (Exception ex){
            logger.error(ex);
            logger.error(String.format(" dto '%s' " , dto.toString()));
            throw new RuntimeException(ex);
        }
        if(utils.isEmpty(task.getId())){
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

}
