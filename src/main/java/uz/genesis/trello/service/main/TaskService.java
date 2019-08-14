package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.*;
import uz.genesis.trello.domain.main.Task;
import uz.genesis.trello.domain.main.TaskTimeEntry;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.*;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.TaskMapper;
import uz.genesis.trello.mapper.main.TaskTimeEntryMapper;
import uz.genesis.trello.mapper.settings.TypeMapper;
import uz.genesis.trello.repository.main.ITaskRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.TaskValidator;

import javax.validation.constraints.NotNull;
import java.sql.Types;
import java.util.List;

@Service
public class TaskService extends AbstractCrudService<TaskDto, TaskCreateDto, TaskUpdateDto, TaskCriteria, ITaskRepository> implements ITaskService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final ITaskCommentService taskCommentService;
    private final ITaskCheckListService taskCheckListService;
    private final ITaskMemberService taskMemberService;
    private final ICheckListGroupService checkListGroupService;
    private final ITaskTagService taskTagService;
    private final TaskTimeEntryMapper taskTimeEntryMapper;
    private final TaskValidator validator;
    private final TypeMapper typeMapper;
    private final TaskMapper mapper;

    @Autowired
    public TaskService(ITaskRepository repository, BaseUtils utils, IErrorRepository errorRepository, GenericMapper genericMapper, ITaskCommentService taskCommentService, ITaskCheckListService taskCheckListService, ITaskMemberService taskMemberService, ICheckListGroupService checkListGroupService, ITaskTagService taskTagService, TaskTimeEntryMapper taskTimeEntryMapper, TaskValidator validator, TypeMapper typeMapper, TaskMapper mapper) {
        super(repository, utils, errorRepository);
        this.genericMapper = genericMapper;
        this.taskCommentService = taskCommentService;
        this.taskCheckListService = taskCheckListService;
        this.taskMemberService = taskMemberService;
        this.checkListGroupService = checkListGroupService;
        this.taskTagService = taskTagService;
        this.taskTimeEntryMapper = taskTimeEntryMapper;
        this.validator = validator;
        this.typeMapper = typeMapper;
        this.mapper = mapper;
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_CREATE)")
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull TaskCreateDto dto) {

        Task task = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(task);
        task.setId(repository.create(dto, "createTask"));
        if (utils.isEmpty(task.getId())) {
            logger.error(String.format("Non TaskCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(Task.class)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(task)), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_UPDATE)")
    public ResponseEntity<DataDto<TaskDto>> update(@NotNull TaskUpdateDto dto) {
        validator.validateOnUpdate(dto);

        if (repository.update(dto, "updateTask")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_UPDATED, utils.toErrorParams(Task.class, dto.getId())));
        }
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_DELETE)")
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_READ)")
    public ResponseEntity<DataDto<TaskDto>> get(Long id) {
        Task task = repository.find(TaskCriteria.childBuilder().selfId(id).build());
        if (utils.isEmpty(task)) {
            logger.error(String.format("task with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(Task.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(task)), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_MOVE)")
    public ResponseEntity<DataDto<TaskDto>> move(MovingTaskDto dto) {
        if (repository.call(dto, "moveTask", Types.BOOLEAN)) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.COULD_NOT_MOVE, utils.toErrorParams(Task.class, dto.getId())));
        }

    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_READ)")
    public ResponseEntity<DataDto<List<TaskDto>>> getAll(TaskCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_CREATE_TIME)")
    public ResponseEntity<DataDto<GenericDto>> createTaskTimeEntry(TaskTimeEntryCreateDto taskTimeEntryCreateDto) {
        TaskTimeEntry taskTimeEntry = taskTimeEntryMapper.fromCreateDto(taskTimeEntryCreateDto);
        taskTimeEntry.setId(repository.call(taskTimeEntryCreateDto, "createTaskTimeEntry", Types.BIGINT));
        if (utils.isEmpty(taskTimeEntry.getId())) {
            logger.error(String.format("Non TaskTimeEntryCreateDto defined '%s' ", new Gson().toJson(taskTimeEntryCreateDto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(TaskTimeEntry.class)));
        }
        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(taskTimeEntry)), HttpStatus.CREATED);
    }

    @Override
    public List<TaskProjectDetailDto> getProjectDetailTask(TaskCriteria criteria) {


        List<TaskProjectDetailDto> tasks = mapper.toTaskProjectDetailDto(repository.findAll(criteria));
        tasks.forEach(dto -> {
            dto.setTagList(taskTagService.getAllTaskTagList(TaskTagCriteria.childBuilder().taskId(dto.getId()).build()));
            dto.setCommentCount(taskCommentService.getCommentCount(TaskCommentCriteria.childBuilder().taskId(dto.getId()).build()));
            dto.setCheckListCount(taskCheckListService.getCheckListCount(TaskCheckListCriteria.childBuilder().taskId(dto.getId()).build()));
        });
        return tasks;
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).TASK_READ_DETAILS)")
    public ResponseEntity<DataDto<TaskDetailsDto>> getTaskDetail(Long id) {
        if (repository.call(GenericDto.builder().id(id).build(), "hasaccesstotask", Types.BOOLEAN)){
            Task task = repository.find(id);

            if (utils.isEmpty(task)){
                logger.error(String.format("task with id '%s' not found", id));
                return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                        .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(Task.class, id)))
                        .build()), HttpStatus.NOT_FOUND);
            }

            List<TaskTagDto> taskTagDtoList = taskTagService.getAllTaskTagList(TaskTagCriteria.childBuilder().taskId(id).build());
            List<TaskMemberDto> taskMemberDtoList = taskMemberService.getAllTaskMemberList(TaskMemberCriteria.childBuilder().taskId(id).build());
            List<CheckListGroupDto> checkListGroupDtoList = checkListGroupService.getAllCheckListGroupList(CheckListGroupCriteria.childBuilder().taskId(id).build());
            List<TaskCommentDto> taskCommentDtoList = taskCommentService.getAllTaskCommentList(TaskCommentCriteria.childBuilder().taskId(id).build());

            TaskDetailsDto taskDetailsDto = TaskDetailsDto.childBuilder()
                    .id(task.getId())
                    .projectId(task.getProjectId())
                    .columnId(task.getColumnId())
                    .name(task.getName())
                    .description(task.getDescription())
                    .startAt(task.getStartAt())
                    .deadLine(task.getDeadLine())
                    .ordering(task.getOrdering())
                    .tags(taskTagDtoList)
                    .members(taskMemberDtoList)
                    .checkListGroups(checkListGroupDtoList)
                    .comments(taskCommentDtoList)
                    .levelType(typeMapper.toDto(task.getTaskLevelType()))
                    .priorityType(typeMapper.toDto(task.getTaskPriorityType()))
                    .build();

            return new ResponseEntity<>(new DataDto<>(taskDetailsDto), HttpStatus.OK);
        }
        throw new AccessDeniedException(errorRepository.getErrorMessage(ErrorCodes.ACCESS_DENIED, utils.toErrorParams("TaskDetails")));
    }
}
