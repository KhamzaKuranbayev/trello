package uz.genesis.trello.service.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.TaskActionCriteria;
import uz.genesis.trello.dto.main.TaskActionDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.main.TaskActionMapper;
import uz.genesis.trello.repository.main.ITaskActionRepository;
import uz.genesis.trello.service.AbstractService;
import uz.genesis.trello.utils.BaseUtils;

import java.util.List;

@Service
public class TaskActionService extends AbstractService<TaskActionDto, TaskActionCriteria, ITaskActionRepository> implements ITaskActionService {
    private final TaskActionMapper mapper;

    @Autowired
    public TaskActionService(ITaskActionRepository repository, BaseUtils utils, TaskActionMapper mapper) {
        super(repository, utils);
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<DataDto<List<TaskActionDto>>> getAll(TaskActionCriteria criteria) {
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria))), HttpStatus.OK);
    }

    @Override
    public List<TaskActionDto> getAllTaskAction(TaskActionCriteria criteria) {
        return mapper.toDto(repository.findAll(criteria));
    }

    @Override
    public ResponseEntity<DataDto<TaskActionDto>> get(Long id) {
        return super.get(id);
    }
}
