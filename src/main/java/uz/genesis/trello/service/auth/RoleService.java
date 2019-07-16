package uz.genesis.trello.service.auth;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.auth.RoleCriteria;
import uz.genesis.trello.domain.auth.Role;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.*;
import uz.genesis.trello.dto.response.AppErrorDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.mapper.auth.RoleMapper;
import uz.genesis.trello.repository.auth.RoleRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.validators.auth.RoleServiceValidator;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class RoleService extends AbstractCrudService<RoleDto, RoleCreateDto, RoleUpdateDto, RoleCriteria, RoleRepository> implements IRoleService {
    protected final Log logger = LogFactory.getLog(getClass());
    private final RoleMapper roleMapper;
    private final GenericMapper genericMapper;
    private final RoleServiceValidator validator;

    @Autowired
    public RoleService(RoleRepository repository, BaseUtils utils, RoleMapper roleMapper, GenericMapper genericMapper, RoleServiceValidator validator) {
        super(repository, utils);
        this.roleMapper = roleMapper;
        this.genericMapper = genericMapper;
        this.validator = validator;
    }

    @Override
    public ResponseEntity<DataDto<GenericDto>> create(@NotNull RoleCreateDto dto) {
        Role role = roleMapper.fromCreateDto(dto);
        validator.validateDomainOnCreate(role);
        try {
            role.setId(repository.create(dto, "createRole"));
        } catch (Exception ex) {
            logger.error(ex);
            logger.error(String.format(" dto '%s' ", dto.toString()));
            throw new RuntimeException(ex);
        }
        if (utils.isEmpty(role.getId())) {
            logger.error(String.format("Non RoleCreatedDto defined '%s' ", new Gson().toJson(dto)));
            throw new RuntimeException(String.format("Non RoleCreatedDto defined '%s' ", new Gson().toJson(dto)));
        }

        return new ResponseEntity<>(new DataDto<>(genericMapper.fromDomain(role)), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DataDto<RoleDto>> get(Long id) {
        Role role = repository.find(RoleCriteria.childBuilder().selfId(id).build());

        if (utils.isEmpty(role)) {
            logger.error(String.format("role with id '%s' not found", id));
            return new ResponseEntity<>(new DataDto<>(AppErrorDto.builder().friendlyMessage(
                    String.format("role with id '%s' not found", id)).build()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new DataDto<>(roleMapper.toDto(role)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<RoleDto>> update(@NotNull RoleUpdateDto dto) {

        validator.validateOnUpdate(dto);
        if (repository.update(dto, "updateRole")) {
            return get(dto.getId());
        } else {
            throw new RuntimeException(String.format("could not update role with id '%s'", dto.getId()));
        }
    }


    @Override
    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        validator.validateOnDelete(id);
        return new ResponseEntity<>(new DataDto<>(repository.delete(id, "deleteRole")), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DataDto<List<RoleDto>>> getAll(RoleCriteria criteria) {
        List<Role> roles = repository.findAll(criteria);
        return new ResponseEntity<>(new DataDto<>(roleMapper.toDto(roles)), HttpStatus.OK);
    }
}
