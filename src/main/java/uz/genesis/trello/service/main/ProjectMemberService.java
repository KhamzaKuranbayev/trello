package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.ProjectMemberCriteria;
import uz.genesis.trello.domain.main.ProjectMember;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.main.ProjectMemberCreateDto;
import uz.genesis.trello.dto.main.ProjectMemberDto;
import uz.genesis.trello.dto.main.ProjectMemberUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.hr.EmployeeMapper;
import uz.genesis.trello.mapper.main.ProjectMemberMapper;
import uz.genesis.trello.repository.main.IProjectMemberRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.ProjectMemberServiceValidator;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = {"projectMembers"})
public class ProjectMemberService extends AbstractCrudService<ProjectMemberDto, ProjectMemberCreateDto, ProjectMemberUpdateDto, ProjectMemberCriteria, IProjectMemberRepository> implements IProjectMemberService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final EmployeeMapper employeeMapper;
    private final ProjectMemberServiceValidator validator;
    private final ProjectMemberMapper mapper;

    public ProjectMemberService(IProjectMemberRepository repository, BaseUtils utils, IErrorRepository errorRepository, GenericMapper genericMapper, EmployeeMapper employeeMapper, ProjectMemberServiceValidator validator, ProjectMemberMapper mapper) {
        super(repository, utils, errorRepository);
        this.genericMapper = genericMapper;
        this.employeeMapper = employeeMapper;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    @CacheEvict(allEntries = true)
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).PROJECT_MEMBER_CREATE)")
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull ProjectMemberCreateDto dto) {
        ProjectMember projectMember = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(projectMember);
        projectMember.setId(repository.create(dto, "createProjectMember"));
        if (utils.isEmpty(projectMember.getId())) {
            logger.error(String.format("Non ProjectMemberCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(ProjectMember.class)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(projectMember)), HttpStatus.CREATED);
    }

    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).PROJECT_MEMBER_READ)")
    public ResponseEntity<DataDto<ProjectMemberDto>> get(Long id) {
        ProjectMember projectMember = repository.find(ProjectMemberCriteria.childBuilder().selfId(id).build());
        if (utils.isEmpty(projectMember)) {
            logger.error(String.format("projectMember with id '%s' not found", id));
            return new  ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(ProjectMember.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(projectMember)), HttpStatus.OK);
    }

//    @Override
//    public ResponseEntity<DataDto<ProjectMemberDto>> update(@NotNull ProjectMemberUpdateDto dto) {
//
//        validator.validateOnUpdate(dto);
//        if (repository.update(dto, "updateProjectMember")) {
//            return get(dto.getId());
//        } else {
//            throw new RuntimeException(String.format("could not update projectMember with id '%s'", dto.getId()));
//        }
//    }
//
//
//    @Override
//    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
//        validator.validateOnDelete(id);
//        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteProjectMember")), HttpStatus.OK);
//    }


    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).PROJECT_MEMBER_READ)")
    public ResponseEntity<DataDto<List<ProjectMemberDto>>> getAll(ProjectMemberCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }

    @Override
    @Cacheable(key = "#root.methodName + #criteria.projectId")
    public List<ProjectMemberDto> getAllProjectMembers(ProjectMemberCriteria criteria){
        return mapper.toDto(repository.findAll(criteria));
    }


    @Override
    @PreAuthorize("hasPermission(null, T(uz.genesis.trello.enums.Permissions).PROJECT_MEMBER_READ_EMPLOYEE)")
    public ResponseEntity<DataDto<List<EmployeeDto>>> getEmployeeListByProjectId(ProjectMemberCriteria criteria) {
        return new ResponseEntity<>(new DataDto<>(repository.findAll(criteria).stream().map(projectMember -> employeeMapper.toDto(projectMember.getEmployee())).collect(Collectors.toList())), HttpStatus.OK);
    }
}
