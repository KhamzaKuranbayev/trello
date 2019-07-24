package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.ProjectTagCriteria;
import uz.genesis.trello.domain.main.ProjectTag;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.ProjectTagCreateDto;
import uz.genesis.trello.dto.main.ProjectTagDto;
import uz.genesis.trello.dto.main.ProjectTagUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.ProjectTagMapper;
import uz.genesis.trello.repository.main.IProjectTagRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.ProjectTagValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@CacheConfig(cacheNames = "projectTags")
public class ProjectTagService extends AbstractCrudService<ProjectTagDto, ProjectTagCreateDto, ProjectTagUpdateDto, ProjectTagCriteria, IProjectTagRepository> implements IProjectTagService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final ProjectTagValidator validator;
    private final ProjectTagMapper mapper;
    @Autowired
    public ProjectTagService(IProjectTagRepository repository, BaseUtils utils, GenericMapper genericMapper, ProjectTagValidator validator, ProjectTagMapper mapper) {
        super(repository, utils);
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.mapper = mapper;
    }

    @Override
    @CacheEvict(allEntries = true)
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull ProjectTagCreateDto dto) {

        ProjectTag projectTag = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(projectTag);
        projectTag.setId(repository.create(dto, "createProjectTag"));
        if (utils.isEmpty(projectTag.getId())) {
            logger.error(String.format("Non ProjectTagCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non ProjectTagCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(projectTag)), HttpStatus.CREATED);
    }

    @Override
    @CacheEvict(allEntries = true)
    public ResponseEntity<DataDto<ProjectTagDto>> update(@NotNull ProjectTagUpdateDto dto) {
        validator.validateOnUpdate(dto);

        if (repository.update(dto, "updateProjectTag")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(String.format("could not update task with  id '%s'", dto.getId()));
        }
    }

    @Override
    @CacheEvict(allEntries = true)
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        if (repository.delete(id, "deleteProjectTag")) {
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } else throw new RuntimeException((String.format("could not delete projectTag with user id '%s'", id)));
    }

    @Override
    public ResponseEntity<DataDto<ProjectTagDto>> get(Long id) {
        ProjectTag projectTag = repository.find(ProjectTagCriteria.childBuilder().selfId(id).build());
        if (utils.isEmpty(projectTag)) {
            logger.error(String.format("projectTag with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("projectTag with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(projectTag)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<ProjectTagDto>>> getAll(ProjectTagCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }

    @Override
    @Cacheable(key = "#root.methodName + #criteria.projectId")
    public List<ProjectTagDto> getAllTag(ProjectTagCriteria criteria){
        return mapper.toDto(repository.findAll(criteria));
    }
}
