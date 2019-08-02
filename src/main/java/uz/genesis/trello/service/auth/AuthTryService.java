package uz.genesis.trello.service.auth;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.auth.AuthTryCriteria;
import uz.genesis.trello.domain.auth.AuthTry;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.AuthTryCreateDto;
import uz.genesis.trello.dto.auth.AuthTryDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.auth.AuthTryMapper;
import uz.genesis.trello.repository.auth.IAuthTryRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.service.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.auth.AuthTryValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class AuthTryService extends AbstractCrudService<AuthTryDto, AuthTryCreateDto, CrudDto, AuthTryCriteria, IAuthTryRepository> implements IAuthTryService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final GenericMapper genericMapper;
    private final AuthTryMapper mapper;
    private final AuthTryValidator validator;

    @Autowired
    public AuthTryService(IAuthTryRepository repository, BaseUtils utils, IErrorRepository errorRepository, GenericMapper genericMapper, AuthTryMapper mapper, AuthTryValidator validator) {
        super(repository, utils, errorRepository);
        this.genericMapper = genericMapper;
        this.mapper = mapper;
        this.validator = validator;
    }


    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull AuthTryCreateDto dto) {
        AuthTry authTry = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(authTry);
        authTry.setId(repository.create(dto, "createAuthTry"));
        if (utils.isEmpty(authTry.getId())) {
            logger.error(String.format("Non AuthTryCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(errorRepository.getErrorMessage(ErrorCodes.OBJECT_COULD_NOT_CREATED, utils.toErrorParams(AuthTry.class)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(authTry)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<AuthTryDto>> get(Long id) {
        AuthTry authTry = repository.find(AuthTryCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(authTry)) {
            logger.error(String.format("authTry with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder()
                    .friendlyMessage(errorRepository.getErrorMessage(ErrorCodes.OBJECT_NOT_FOUND_ID, utils.toErrorParams(AuthTry.class, id)))
                    .build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(authTry)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<AuthTryDto>>> getAll(AuthTryCriteria criteria) {
        List<AuthTry> authTryList = repository.findAll(criteria);
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(authTryList), total), HttpStatus.OK);
    }
}
