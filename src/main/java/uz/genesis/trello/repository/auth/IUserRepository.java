package uz.genesis.trello.repository.auth;

import uz.genesis.trello.criterias.auth.UserCriteria;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.repository.IGenericCrudRepository;
import uz.genesis.trello.repository.IGenericRepository;

/**
 * Created by 'javokhir' on 11/06/2019
 */

public interface IUserRepository extends IGenericCrudRepository<User, UserCriteria> {

}
