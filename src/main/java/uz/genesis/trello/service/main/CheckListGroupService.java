package uz.genesis.trello.service.main;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.main.CheckListGroupCriteria;
import uz.genesis.trello.domain.main.CheckListGroup;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.main.CheckListGroupCreateDto;
import uz.genesis.trello.dto.main.CheckListGroupDto;
import uz.genesis.trello.dto.main.CheckListGroupUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.main.CheckListGroupMapper;
import uz.genesis.trello.repository.main.ICheckListGroupRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.service.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.main.CheckListGroupValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class CheckListGroupService extends AbstractCrudService<CheckListGroupDto, CheckListGroupCreateDto, CheckListGroupUpdateDto, CheckListGroupCriteria, ICheckListGroupRepository> implements ICheckListGroupService {

    @Autowired
    public CheckListGroupService(ICheckListGroupRepository repository, BaseUtils utils, IErrorRepository errorRepository, CheckListGroupMapper mapper, CheckListGroupValidator validator, GenericMapper genericMapper) {
        super(repository, utils, errorRepository);
        this.mapper = mapper;
        this.validator = validator;
        this.genericMapper = genericMapper;
    }

    protected final Log logger = LogFactory.getLog(getClass());
    private final CheckListGroupMapper mapper;
    private final CheckListGroupValidator validator;
    private final GenericMapper genericMapper;


    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull CheckListGroupCreateDto dto) {
        CheckListGroup checkListGroup = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(checkListGroup);
        checkListGroup.setId(repository.create(dto, "createCheckListGroup"));
        if (utils.isEmpty(checkListGroup.getId())) {
            logger.error(String.format("Non CheckListGroupCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non CheckListGroupCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(checkListGroup)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<CheckListGroupDto>> get(Long id) {
        CheckListGroup projectColumn = repository.find(CheckListGroupCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(projectColumn)) {
            logger.error(String.format("checkListGroup with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("checkListGroup with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(projectColumn)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<CheckListGroupDto>> update(@NotNull CheckListGroupUpdateDto dto) {

        validator.validateOnUpdate(dto);
        if (repository.update(dto, "updateCheckListGroup")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(String.format("could not update checkListGroup with id '%s'", dto.getId()));
        }
    }

    @Override
    @CacheEvict(value = {"checkLists"}, allEntries = true)
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteCheckListGroup")), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<CheckListGroupDto>>> getAll(CheckListGroupCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }
}
