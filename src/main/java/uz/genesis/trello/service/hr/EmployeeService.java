package uz.genesis.trello.service.hr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.hr.EmployeeMapper;
import uz.genesis.trello.repository.hr.IEmployeeRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.service.auth.IUserService;
import uz.genesis.trello.repository.settings.IErrorRepository;
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
    private final EmployeeMapper mapper;
    private EmployeeServiceValidator validator;
    private IUserService userService;

    @Autowired
    public EmployeeService(IEmployeeRepository repository, BaseUtils utils, IErrorRepository errorRepository, GenericMapper genericMapper, EmployeeServiceValidator validator, EmployeeMapper mapper, IUserService userService) {
        super(repository, utils, errorRepository);
        this.genericMapper = genericMapper;
        this.validator = validator;
        this.mapper = mapper;
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
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(Employee.class)));
        }

    }

    @Override
    @CacheEvict(value = {"projectMembers"}, allEntries = true)
    public ResponseEntity<DataDto<EmployeeDto>> update(@NotNull EmployeeUpdateDto dto) {
        validator.validateDomainOnUpdate(mapper.fromUpdateDto(dto));

        if (repository.update(dto, "updateEmployee")) {
            return get(dto.getUserId());
        } else {
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_UPDATED, utils.toErrorParams(Employee.class)));
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<EmployeeDto>> get(Long userId) {
        Employee employee = repository.find(EmployeeCriteria.childBuilder().selfId(userId).build());
        if (utils.isEmpty(employee)) {
            logger.error(String.format("employee with id '%s' not found", userId));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(Employee.class, userId)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(employee)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<EmployeeDto>>> getAll(EmployeeCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria)), total), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<EmployeeDto>>> getAllWithPhoto(EmployeeCriteria criteria) {
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(repository.getEmployeesWithPhoto(criteria), total), HttpStatus.OK);
    }
}
