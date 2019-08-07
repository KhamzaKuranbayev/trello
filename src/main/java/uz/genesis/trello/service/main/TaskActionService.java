package uz.genesis.trello.service.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.TaskActionCriteria;
import uz.genesis.trello.domain.main.TaskAction;
import uz.genesis.trello.dto.main.TaskActionDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.main.TaskActionMapper;
import uz.genesis.trello.repository.main.ITaskActionRepository;
import uz.genesis.trello.service.AbstractService;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;

import java.util.List;

@Service
public class TaskActionService extends AbstractService<TaskActionDto, TaskActionCriteria, ITaskActionRepository> implements ITaskActionService {
    private final TaskActionMapper mapper;
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    public TaskActionService(ITaskActionRepository repository, BaseUtils utils, IErrorRepository errorRepository, TaskActionMapper mapper) {
        super(repository, utils, errorRepository);
        this.mapper = mapper;
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_ACTION_READ)")
    public ResponseEntity<DataDto<List<TaskActionDto>>> getAll(TaskActionCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }

    @Override
    public List<TaskActionDto> getAllTaskAction(TaskActionCriteria criteria) {
        return mapper.toDto(repository.findAll(criteria));
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_ACTION_READ)")
    public ResponseEntity<DataDto<TaskActionDto>> get(Long id) {
        TaskAction taskAction = repository.find(TaskActionCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(taskAction)) {
            logger.error(String.format("taskAction with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(TaskAction.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(taskAction)), HttpStatus.OK);
    }
}
