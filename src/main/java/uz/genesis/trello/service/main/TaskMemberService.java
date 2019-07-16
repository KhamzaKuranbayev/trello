package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.TaskMemberCriteria;
import uz.genesis.trello.domain.main.TaskMember;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.TaskMemberCreateDto;
import uz.genesis.trello.dto.main.TaskMemberDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.TaskMemberMapper;
import uz.genesis.trello.repository.main.ITaskMemberRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.TaskMemberValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class TaskMemberService extends AbstractCrudService<TaskMemberDto, TaskMemberCreateDto, CrudDto, TaskMemberCriteria, ITaskMemberRepository> implements ITaskMemberService {


    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final TaskMemberValidator validator;
    private final TaskMemberMapper mapper;

    public TaskMemberService(ITaskMemberRepository repository, BaseUtils utils, GenericMapper genericMapper, TaskMemberValidator validator, TaskMemberMapper mapper) {
        super(repository, utils);
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull TaskMemberCreateDto dto) {

        TaskMember taskMember = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(taskMember);
        taskMember.setId(repository.create(dto, "createTaskMember"));
        if (utils.isEmpty(taskMember.getId())) {
            logger.error(String.format("Non TaskMemberCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non TaskMemberCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(taskMember)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        if (repository.delete(id, "deleteTaskMember")) {
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } else throw new RuntimeException((String.format("could not delete taskMember with user id '%s'", id)));
    }

    @Override
    public ResponseEntity<DataDto<TaskMemberDto>> get(Long id) {
        TaskMember taskmember = repository.find(TaskMemberCriteria.childBuilder().selfId(id).build());
        if (utils.isEmpty(taskmember)) {
            logger.error(String.format("taskMember with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("taskMember with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(taskmember)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<TaskMemberDto>>> getAll(TaskMemberCriteria criteria) {
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria))), HttpStatus.OK);
    }
}
