package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.TaskCheckListCriteria;
import uz.genesis.trello.domain.main.TaskCheckList;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.CheckListCountDto;
import uz.genesis.trello.dto.main.TaskCheckListCreateDto;
import uz.genesis.trello.dto.main.TaskCheckListDto;
import uz.genesis.trello.dto.main.TaskCheckListUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.TaskCheckListMapper;
import uz.genesis.trello.repository.main.ITaskCheckListRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.TaskCheckListValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"checkLists"})
public class TaskCheckListService extends AbstractCrudService<TaskCheckListDto, TaskCheckListCreateDto, TaskCheckListUpdateDto, TaskCheckListCriteria, ITaskCheckListRepository> implements ITaskCheckListService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final TaskCheckListValidator validator;
    private final TaskCheckListMapper mapper;

    public TaskCheckListService(ITaskCheckListRepository repository, BaseUtils utils, IErrorRepository errorRepository, GenericMapper genericMapper, TaskCheckListValidator validator, TaskCheckListMapper mapper) {
        super(repository, utils, errorRepository);
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    @CacheEvict(allEntries = true)
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull TaskCheckListCreateDto dto) {

        TaskCheckList taskCheckList = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(taskCheckList);
        taskCheckList.setId(repository.create(dto, "createTaskCheckList"));
        if (utils.isEmpty(taskCheckList.getId())) {
            logger.error(String.format("Non TaskCheckListCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(TaskCheckList.class)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(taskCheckList)), HttpStatus.CREATED);
    }

    @Override
    @CacheEvict(allEntries = true)
    public ResponseEntity<DataDto<TaskCheckListDto>> update(@NotNull TaskCheckListUpdateDto dto) {
        validator.validateDomainOnUpdate(mapper.fromUpdateDto(dto));

        if (repository.update(dto, "updateTaskChecklist")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_UPDATED, utils.toErrorParams(TaskCheckList.class, dto.getId())));
        }
    }

    @Override
    @CacheEvict(allEntries = true)
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<TaskCheckListDto>> get(Long id) {
        TaskCheckList taskCheckList = repository.find(TaskCheckListCriteria.childBuilder().selfId(id).build());
        if (utils.isEmpty(taskCheckList)) {
            logger.error(String.format("taskCheckList with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(TaskCheckList.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(taskCheckList)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<TaskCheckListDto>>> getAll(TaskCheckListCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }

    @Override
    @Cacheable(key = "#root.methodName + #criteria.taskId")
    public CheckListCountDto getCheckListCount(TaskCheckListCriteria criteria) {
        criteria.setProjectDetail(true);
        Long totalCount = repository.getTotalCount(criteria);
        criteria.setChecked(true);
        Long checkedCount = repository.getTotalCount(criteria);
        return CheckListCountDto.builder().totalCount(totalCount).checkedCount(checkedCount).build();
    }
}
