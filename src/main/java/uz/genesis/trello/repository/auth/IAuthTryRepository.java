package uz.genesis.trello.repository.auth;

import uz.genesis.trello.criterias.auth.AuthTryCriteria;
import uz.genesis.trello.domain.auth.AuthTry;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface IAuthTryRepository extends IGenericCrudRepository<AuthTry, AuthTryCriteria> {
}
