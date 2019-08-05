package uz.genesis.trello.service.hr;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.hr.GroupCriteria;
import uz.genesis.trello.domain.hr.Group;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.GroupCreateDto;
import uz.genesis.trello.dto.hr.GroupDto;
import uz.genesis.trello.dto.hr.GroupUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.hr.GroupMapper;
import uz.genesis.trello.repository.hr.IGroupRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.hr.GroupServiceValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"groups"})
public class GroupService extends AbstractCrudService<GroupDto, GroupCreateDto, GroupUpdateDto, GroupCriteria, IGroupRepository> implements IGroupService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final GroupServiceValidator validator;
    private final GroupMapper groupMapper;

    @Autowired
    public GroupService(IGroupRepository repository, BaseUtils utils, IErrorRepository errorRepository, GenericMapper genericMapper, GroupServiceValidator validator, GroupMapper groupMapper) {
        super(repository, utils, errorRepository);
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.groupMapper = groupMapper;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull GroupCreateDto dto) {

        Group group = groupMapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(group);
        group.setId(repository.create(dto, "createGroup"));
        if (utils.isEmpty(group.getId())) {
            logger.error(String.format("Non GroupCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(Group.class)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(group)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<GroupDto>> get(Long id) {
        Group group = repository.find(id);
        if (utils.isEmpty(group)) {
            logger.error(String.format("group with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(Group.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(groupMapper.toDto(group)), HttpStatus.OK);
    }

    @Override
    @CacheEvict(value = {"employeeGroups"}, allEntries = true)
    public ResponseEntity<DataDto<GroupDto>> update(@NotNull GroupUpdateDto dto) {

        validator.validateDomainOnUpdate(groupMapper.fromUpdateDto(dto));
        if (repository.update(dto, "updateGroup")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_UPDATED, utils.toErrorParams(Group.class, dto.getId())));
        }
    }


    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteGroup")), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<GroupDto>>> getAll(GroupCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(groupMapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }
}
