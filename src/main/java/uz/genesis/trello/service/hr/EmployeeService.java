package uz.genesis.trello.service.hr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.hr.EmployeeCriteria;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.EmployeeCreateDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.hr.EmployeeUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.hr.EmployeeMapper;
import uz.genesis.trello.repository.hr.IEmployeeRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.service.auth.IUserService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.hr.EmployeeServiceValidator;

import javax.validation.constraints.NotNull;
import java.sql.Types;
import java.util.List;
import java.util.Objects;

/**
 * Created by 'javokhir' on 04/07/2019
 */

@Service
public class EmployeeService extends AbstractCrudService<EmployeeDto, EmployeeCreateDto, EmployeeUpdateDto, EmployeeCriteria, IEmployeeRepository> implements IEmployeeService {


    /**
     * Common logger for use in subclasses.
     */
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private EmployeeServiceValidator validator;
    private final EmployeeMapper mapper;
    private IUserService userService;

    @Autowired
    public EmployeeService(IEmployeeRepository repository, BaseUtils utils, EmployeeServiceValidator validator, EmployeeMapper mapper, IUserService userService) {
        super(repository, utils);
        this.mapper = mapper;
        this.genericMapper = Mappers.getMapper(GenericMapper.class);
        this.validator = validator;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull EmployeeCreateDto dto) {

        validator.validateOnCreate(dto);

        if (!utils.isEmpty(dto.getUser())) {
            ResponseEntity<DataDto<GenericDto>> userResult = userService.create(dto.getUser());
            if (!utils.isEmpty(userResult.getBody()) && !utils.isEmpty(Objects.requireNonNull(userResult.getBody()).getData())) {
                Long userId = userResult.getBody().getData().getId();
                if (!utils.isEmpty(userId)) {
                    dto.setUserId(userId);
                }
            }
        }

        if (repository.call(dto, "createEmployee", Types.BOOLEAN)) {
            return new ResponseEntity<>(new DataDto<>(new GenericDto(dto.getUserId())), HttpStatus.CREATED);
        } else {
            throw new RuntimeException(String.format("cannot create employee with this user id '%s'", dto.getUserId()));
        }

    }

    @Override
    @CacheEvict(value = {"projectMembers"}, allEntries = true)
    public ResponseEntity<DataDto<EmployeeDto>> update(@NotNull EmployeeUpdateDto dto) {
        validator.validateOnUpdate(dto);

        if (repository.update(dto, "updateEmployee")) {
            return get(dto.getUserId());
        } else {
            throw new RuntimeException(String.format("could not update employee with user id '%s'", dto.getUserId()));
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        if (repository.delete(id, "deleteEmployee")) {
            return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
        } else throw new RuntimeException((String.format("could not delete employee with user id '%s'", id)));
    }

    @Override
    public ResponseEntity<DataDto<EmployeeDto>> get(Long userId) {
        Employee employee = repository.find(EmployeeCriteria.childBuilder().selfId(userId).build());
        if (utils.isEmpty(employee)) {
            logger.error(String.format("employee with id '%s' not found", userId));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("employee with id '%s' not found", userId)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(employee)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<EmployeeDto>>> getAll(EmployeeCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }
}
