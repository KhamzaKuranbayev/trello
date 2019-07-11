package uz.genesis.trello.repository.auth;

import uz.genesis.trello.criterias.auth.PermissionCriteria;
import uz.genesis.trello.domain.auth.Permission;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface IPermissionRepository extends IGenericCrudRepository<Permission, PermissionCriteria> {
}
