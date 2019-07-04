package uz.genesis.trello.service.hr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.auth.EmployeeCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.EmployeeCreateDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.hr.EmployeeUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.repository.auth.IEmployeeRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.service.auth.IUserService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.hr.EmployeeServiceValidator;

import javax.validation.constraints.NotNull;
import java.sql.Types;
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
    private GenericMapper genericMapper;
    private EmployeeServiceValidator validator;
    private IUserService userService;

    public EmployeeService(IEmployeeRepository repository, BaseUtils utils, EmployeeServiceValidator validator, IUserService userService) {
        super(repository, utils);
        this.genericMapper = Mappers.getMapper(GenericMapper.class);
        this.validator = validator;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull EmployeeCreateDto dto) {

        validator.validateOnCreateWithUser(dto);

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
}
