package uz.genesis.trello.service.auth;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.auth.RoleCriteria;
import uz.genesis.trello.dto.auth.*;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.IGenericCrudService;

import java.util.List;
import java.util.Map;

public interface IRoleService extends IGenericCrudService<RoleDto, RoleCreateDto, RoleUpdateDto, Long, RoleCriteria> {

    ResponseEntity<DataDto<List<RoleDto>>> getAllByCriteria(RoleCriteria criteria);
}
