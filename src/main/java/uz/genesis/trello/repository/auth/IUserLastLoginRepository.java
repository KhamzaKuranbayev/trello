package uz.genesis.trello.repository.auth;

import uz.genesis.trello.criterias.auth.UserLastLoginCriteria;
import uz.genesis.trello.domain.auth.UserLastLogin;
import uz.genesis.trello.repository.IGenericCrudRepository;


public interface IUserLastLoginRepository extends IGenericCrudRepository<UserLastLogin, UserLastLoginCriteria> {
}
