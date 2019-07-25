package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.hr.EmployeeCriteria;
import uz.genesis.trello.criterias.main.*;
import uz.genesis.trello.domain.main.Project;
import uz.genesis.trello.domain.main.ProjectDetailDto;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.EmployeeGroupDto;
import uz.genesis.trello.dto.main.*;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.hr.EmployeeMapper;
import uz.genesis.trello.mapper.main.ProjectMapper;
import uz.genesis.trello.mapper.settings.TypeMapper;
import uz.genesis.trello.repository.main.IProjectRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.service.hr.IEmployeeGroupService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.ProjectServiceValidator;

import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService extends AbstractCrudService<ProjectDto, ProjectCreateDto, ProjectUpdateDto, ProjectCriteria, IProjectRepository> implements IProjectService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final TypeMapper typeMapper;
    private final IProjectTagService projectTagService;
    private final EmployeeMapper employeeMapper;
    private final ProjectServiceValidator validator;
    private final IProjectMemberService projectMemberService;
    private final ITaskActionService taskActionService;
    private final ProjectMapper mapper;
    private final IProjectColumnService projectColumnService;
    private final ITaskService taskService;
    private final IEmployeeGroupService employeeGroupService;

    @Autowired
    public ProjectService(IProjectRepository repository, BaseUtils utils, GenericMapper genericMapper, TypeMapper typeMapper, ProjectTagService projectTagService, EmployeeMapper employeeMapper, ProjectServiceValidator validator, IProjectMemberService projectMemberService, ITaskActionService taskActionService, ProjectMapper mapper, IProjectColumnService projectColumnService, ITaskService taskService, IEmployeeGroupService employeeGroupService) {
        super(repository, utils);
        this.genericMapper = genericMapper;
        this.typeMapper = typeMapper;
        this.projectTagService = projectTagService;
        this.employeeMapper = employeeMapper;
        this.validator = validator;
        this.projectMemberService = projectMemberService;
        this.taskActionService = taskActionService;
        this.mapper = mapper;
        this.projectColumnService = projectColumnService;
        this.taskService = taskService;
        this.employeeGroupService = employeeGroupService;
    }


    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull ProjectCreateDto dto) {

        Project project = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(project);
        project.setId(repository.create(dto, "createProject"));
        if (utils.isEmpty(project.getId())) {
            logger.error(String.format("Non ProjectCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non ProjectCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(project)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<ProjectDto>> get(Long id) {
        Project project = repository.find(ProjectCriteria.childBuilder().selfId(id).build());
        if (utils.isEmpty(project)) {
            logger.error(String.format("project with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("project with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(project)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<ProjectDto>> update(@NotNull ProjectUpdateDto dto) {

        validator.validateOnUpdate(dto);
        if (repository.update(dto, "updateProject")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(String.format("could not update project with id '%s'", dto.getId()));
        }
    }


    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteProject")), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<ProjectDto>>> getAll(ProjectCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<ProjectDetailDto>> getProjectDetail(Long id) {
        if(repository.call(GenericDto.builder().id(id).build(), "hasaccesstoproject", Types.BOOLEAN)){
            Project project = repository.find(id);
            List<EmployeeGroupDto> employeeGroupDtoList = new ArrayList<>();
            if (utils.isEmpty(project)) {
                logger.error(String.format("project with id '%s' not found", id));
                return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                        String.format("project with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
            }
            if(!utils.isEmpty(project.getGroup())){
                employeeGroupDtoList = employeeGroupService.getAllEmployeeGroup(EmployeeCriteria.childBuilder().groupId(project.getGroup().getId()).build());
            }
            List<ProjectTagDto> projectTagDtoList = projectTagService.getAllTag(ProjectTagCriteria.childBuilder().projectId(id).build());
            List<ProjectColumnDetailDto> projectColumnDtoList = attachTaskToProjectColumn(projectColumnService.getAllColumns(ProjectColumnCriteria.childBuilder().projectId(id).build()));
            List<ProjectMemberDto> projectMemberDtoList = projectMemberService.getAllProjectMembers(ProjectMemberCriteria.childBuilder().projectId(id).build());
            List<TaskActionDto> taskActionDtoList = taskActionService.getAllTaskAction(TaskActionCriteria.childBuilder().projectId(id).sortBy("id").sortDirection("desc").perPage(20).page(0).build());

            ProjectDetailDto detailDto = ProjectDetailDto.childBuilder()
                    .id(project.getId())
                    .name(project.getName())
                    .codeName(project.getCodeName())
                    .projectType(typeMapper.toDto(project.getProjectType()))
//                    .projectLevelType(typeMapper.toDto(project.getProjectLevelType()))
//                    .projectPriorityType(typeMapper.toDto(project.getProjectPriorityType()))
                    .manager(employeeMapper.toDto(project.getManager()))
                    .employeeGroups(employeeGroupDtoList)
                    .columns(projectColumnDtoList)
                    .tags(projectTagDtoList)
                    .members(projectMemberDtoList)
                    .actions(taskActionDtoList)
                    .build();
            return new ResponseEntity<>(new DataDto<>(detailDto), HttpStatus.OK);
        }
        throw new ValidationException("You are not member of this project");

    }

    @Override
    public ResponseEntity<DataDto<List<ProjectPercentageDto>>> getAllWithPercentage(ProjectCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(repository.getAllPercentageProjects(criteria), total), HttpStatus.OK);
    }

    private List<ProjectColumnDetailDto> attachTaskToProjectColumn(List<ProjectColumnDetailDto> dtoList){
        dtoList.forEach(dto -> dto.setTasks(taskService.getProjectDetailTask(TaskCriteria.childBuilder().projectId(dto.getProjectId()).columnId(dto.getId()).build())));
        return dtoList;
    }
}
