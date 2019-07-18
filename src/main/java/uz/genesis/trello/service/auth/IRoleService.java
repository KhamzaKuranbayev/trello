package uz.genesis.trello.service.auth;

import uz.genesis.trello.criterias.auth.RoleCriteria;
import uz.genesis.trello.dto.auth.*;
import uz.genesis.trello.service.IGenericCrudService;

import java.util.List;


public interface IRoleService extends IGenericCrudService<RoleDto, RoleCreateDto, RoleUpdateDto, Long, RoleCriteria> {

    List<RoleDto> getUserRoles(Long userId);
}
