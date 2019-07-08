package uz.genesis.trello.service.hr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.hr.GroupMapper;
import uz.genesis.trello.repository.hr.IGroupRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.hr.GroupServiceValidator;

import javax.validation.constraints.NotNull;

@Service
public class GroupService extends AbstractCrudService<GroupDto, GroupCreateDto, GroupUpdateDto, GroupCriteria, IGroupRepository> implements IGroupService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final GroupServiceValidator validator;
    private final GroupMapper groupMapper;
    @Autowired
    public GroupService(IGroupRepository repository, BaseUtils utils, GenericMapper genericMapper, GroupServiceValidator validator, GroupMapper groupMapper) {
        super(repository, utils);
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.groupMapper = groupMapper;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull GroupCreateDto dto) {
        return super.create(dto);
    }

    @Override
    public ResponseEntity<DataDto<GroupDto>> get(Long id) {
        Group group = repository.find(GroupCriteria.childBuilder().selfId(id).build());
        if(utils.isEmpty(group)){
            logger.error(String.format("group with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("group with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(groupMapper.toDto(group)), HttpStatus.OK);
    }
}
