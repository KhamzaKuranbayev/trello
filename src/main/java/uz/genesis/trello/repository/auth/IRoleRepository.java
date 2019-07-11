package uz.genesis.trello.repository.auth;

import uz.genesis.trello.criterias.auth.RoleCriteria;
import uz.genesis.trello.domain.auth.Role;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface IRoleRepository extends IGenericCrudRepository<Role, RoleCriteria>  {
}
