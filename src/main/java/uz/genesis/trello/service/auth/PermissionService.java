package uz.genesis.trello.service.auth;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.auth.PermissionCriteria;
import uz.genesis.trello.domain.auth.Permission;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.PermissionCreateDto;
import uz.genesis.trello.dto.auth.PermissionDto;
import uz.genesis.trello.dto.auth.PermissionUpdateDto;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.auth.PermissionMapper;
import uz.genesis.trello.repository.auth.PermissionRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.auth.PermissionServiceValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class PermissionService extends AbstractCrudService<PermissionDto, PermissionCreateDto, PermissionUpdateDto, PermissionCriteria, PermissionRepository> implements IPermissionService {

    protected final Log logger = LogFactory.getLog(getClass());

    private final PermissionMapper mapper;
    private final GenericMapper genericMapper;
    private final PermissionServiceValidator validator;

    @Autowired
    public PermissionService(PermissionRepository repository, BaseUtils utils, PermissionMapper mapper, GenericMapper genericMapper, PermissionServiceValidator validator) {
        super(repository, utils);
        this.mapper = mapper;
        this.genericMapper = genericMapper;
        this.validator = validator;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull PermissionCreateDto dto) {
        Permission permission = mapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(permission);
        permission.setId(repository.create(dto, "createPermission"));
        if (utils.isEmpty(permission.getId())) {
            logger.error(String.format("Non PermissionCreateDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non PermissionCreateDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(permission)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<PermissionDto>> get(Long id) {
        Permission permission = repository.find(PermissionCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(permission)) {
            logger.error(String.format("permission with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("permission with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(permission)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<PermissionDto>> update(@NotNull PermissionUpdateDto dto) {

        validator.validateOnUpdate(dto);
        if (repository.update(dto, "updatePermission")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(String.format("could not update permission with id '%s'", dto.getId()));
        }
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deletePermission")), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<PermissionDto>>> getAll(PermissionCriteria criteria) {
        return new ResponseEntity<>(new DataDto<>(mapper.toDto(repository.findAll(criteria))), HttpStatus.OK);
    }
}
