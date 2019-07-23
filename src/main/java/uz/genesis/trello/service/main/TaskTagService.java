package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.TaskTagCriteria;
import uz.genesis.trello.domain.main.TaskTag;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.TaskTagCreateDto;
import uz.genesis.trello.dto.main.TaskTagDto;
import uz.genesis.trello.dto.main.TaskTagUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.TaskTagMapper;
import uz.genesis.trello.repository.main.ITaskTagRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.TaskTagValidator;

import javax.validation.constraints.NotNull;
import java.util.List;


@Service
public class TaskTagService extends AbstractCrudService<TaskTagDto, TaskTagCreateDto, TaskTagUpdateDto, TaskTagCriteria, ITaskTagRepository> implements ITaskTagService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final TaskTagValidator validator;
    private final TaskTagMapper mapper;
    public TaskTagService(ITaskTagRepository repository, BaseUtils utils, GenericMapper genericMapper, TaskTagValidator validator, TaskTagMapper mapper) {
        super(repository, utils);
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull TaskTagCreateDto dto) {

        TaskTag tasktag = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(tasktag);
        tasktag.setId(repository.create(dto, "createProjectTaskTag"));
        if (utils.isEmpty(tasktag.getId())) {
            logger.error(String.format("Non TaskTagCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non TaskTagCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(tasktag)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<TaskTagDto>> update(@NotNull TaskTagUpdateDto dto) {
        validator.validateOnUpdate(dto);

        if (repository.update(dto, "updateProjectTaskTag")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(String.format("could not update task with  id '%s'", dto.getId()));
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        if (repository.delete(id, "deleteProjectTaskTag")) {
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } else throw new RuntimeException((String.format("could not delete taskTag with user id '%s'", id)));
    }

    @Override
    public ResponseEntity<DataDto<TaskTagDto>> get(Long id) {
        TaskTag tasktag = repository.find(TaskTagCriteria.childBuilder().selfId(id).build());
        if (utils.isEmpty(tasktag)) {
            logger.error(String.format("taskTag with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("taskTag with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(tasktag)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<TaskTagDto>>> getAll(TaskTagCriteria criteria) {
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria))), HttpStatus.OK);
    }

    @Override
    public List<TaskTagDto> getAllTaskTagList(TaskTagCriteria criteria) {
        return mapper.toDto(repository.findAll(criteria));
    }
}
