package uz.genesis.trello.service.auth;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.auth.UserLastLoginCriteria;
import uz.genesis.trello.domain.auth.UserLastLogin;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.UserLastLoginCreateDto;
import uz.genesis.trello.dto.auth.UserLastLoginDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.auth.UserLastLoginMapper;
import uz.genesis.trello.repository.auth.IUserLastLoginRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.auth.UserLastLoginValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class UserLastLoginService extends AbstractCrudService<UserLastLoginDto, UserLastLoginCreateDto, CrudDto, UserLastLoginCriteria, IUserLastLoginRepository> implements IUserLastLoginService{
    private final UserLastLoginMapper mapper;
    protected final Log logger = LogFactory.getLog(getClass());
    private final UserLastLoginValidator validator;
    private final GenericMapper genericMapper;

    public UserLastLoginService(IUserLastLoginRepository repository, BaseUtils utils, UserLastLoginMapper mapper, UserLastLoginValidator validator, GenericMapper genericMapper) {
        super(repository, utils);
        this.mapper = mapper;
        this.validator = validator;
        this.genericMapper = genericMapper;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull UserLastLoginCreateDto dto) {
        UserLastLogin userLastLogin = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(userLastLogin);
        userLastLogin.setId(repository.create(dto, "createUserLastLogin"));
        if (utils.isEmpty(userLastLogin.getId())) {
            logger.error(String.format("Non UserLastLoginCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non UserLastLoginCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(userLastLogin)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<UserLastLoginDto>> get(Long id) {
        UserLastLogin userLastLogin = repository.find(UserLastLoginCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(userLastLogin)) {
            logger.error(String.format("userLastLogin with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("userLastLogin with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(userLastLogin)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<UserLastLoginDto>>> getAll(UserLastLoginCriteria criteria) {
        List<UserLastLogin> userLastLoginList = repository.findAll(criteria);
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(userLastLoginList), total), HttpStatus.OK);
    }

}
