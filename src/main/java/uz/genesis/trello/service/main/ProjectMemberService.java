package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.ProjectMemberCriteria;
import uz.genesis.trello.domain.main.ProjectMember;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.*;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.ProjectMemberMapper;
import uz.genesis.trello.repository.main.IProjectMemberRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.ProjectMemberServiceValidator;

import javax.validation.constraints.NotNull;
@Service
public class ProjectMemberService extends AbstractCrudService<ProjectMemberDto, ProjectMemberCreateDto, ProjectMemberUpdateDto, ProjectMemberCriteria, IProjectMemberRepository> implements IProjectMemberService{
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final ProjectMemberServiceValidator validator;
    private final ProjectMemberMapper mapper;

    public ProjectMemberService(IProjectMemberRepository repository, BaseUtils utils, GenericMapper genericMapper, ProjectMemberServiceValidator validator, ProjectMemberMapper mapper) {
        super(repository, utils);
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull ProjectMemberCreateDto dto) {

        ProjectMember projectMember = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(projectMember);
        try{
            projectMember.setId(repository.create(dto, "createProjectMember"));
        }catch (Exception ex){
            logger.error(ex);
            logger.error(String.format(" dto '%s' " , dto.toString()));
            throw new RuntimeException(ex);
        }
        if(utils.isEmpty(projectMember.getId())){
            logger.error(String.format("Non ProjectMemberCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non ProjectMemberCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(projectMember)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<ProjectMemberDto>> get(Long id) {
        ProjectMember projectMember = repository.find(ProjectMemberCriteria.childBuilder().selfId(id).build());
        if(utils.isEmpty(projectMember)){
            logger.error(String.format("projectMember with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("projectMember with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(projectMember)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<ProjectMemberDto>> update(@NotNull ProjectMemberUpdateDto dto) {

        validator.validateOnUpdate(dto);
        if (repository.update(dto, "updateProjectMember")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(String.format("could not update projectMember with id '%s'", dto.getId()));
        }
    }


    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteProjectMember")), HttpStatus.OK);
    }

}
