package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.ProjectColumnCriteria;
import uz.genesis.trello.domain.main.ProjectColumn;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.ProjectColumnCreateDto;
import uz.genesis.trello.dto.main.ProjectColumnDto;
import uz.genesis.trello.dto.main.ProjectColumnUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.ProjectColumnMapper;
import uz.genesis.trello.repository.main.IProjectColumnRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.ProjectColumnServiceValidator;

import javax.validation.constraints.NotNull;

@Service
public class ProjectColumnService extends AbstractCrudService<ProjectColumnDto, ProjectColumnCreateDto, ProjectColumnUpdateDto, ProjectColumnCriteria, IProjectColumnRepository> implements IProjectColumnService {
    protected final Log logger = LogFactory.getLog(getClass());

    private final ProjectColumnMapper mapper;
    private final GenericMapper genericMapper;
    private final ProjectColumnServiceValidator validator;

    @Autowired
    public ProjectColumnService(IProjectColumnRepository repository, BaseUtils utils, ProjectColumnMapper mapper, GenericMapper genericMapper, ProjectColumnServiceValidator validator) {
        super(repository, utils);
        this.mapper = mapper;
        this.genericMapper = genericMapper;
        this.validator = validator;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull ProjectColumnCreateDto dto) {
        ProjectColumn projectColumn = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(projectColumn);
        try{
            projectColumn.setId(repository.create(dto, "createProjectColumn"));
        }catch (Exception ex){
            logger.error(ex);
            logger.error(String.format(" dto '%s' " , dto.toString()));
            throw new RuntimeException(ex);
        }
        if(utils.isEmpty(projectColumn.getId())){
            logger.error(String.format("Non ProjectColumnCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non ProjectColumnCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(projectColumn)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<ProjectColumnDto>> get(Long id) {
        ProjectColumn projectColumn = repository.find(ProjectColumnCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(projectColumn)) {
            logger.error(String.format("projectColumn with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("projectColumn with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(projectColumn)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<ProjectColumnDto>> update(@NotNull ProjectColumnUpdateDto dto) {

        validator.validateOnUpdate(dto);
        if (repository.update(dto, "updateProjectColumn")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(String.format("could not update projectColumn with id '%s'", dto.getId()));
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteProjectColumn")), HttpStatus.OK);
    }
}
