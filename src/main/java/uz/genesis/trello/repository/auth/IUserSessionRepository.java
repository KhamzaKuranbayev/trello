package uz.genesis.trello.repository.auth;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.domain.auth.User;
import uz.genesis.trello.repository.ICommonRepository;

/**
 * Created by 'javokhir' on 12/06/2019
 */

@Repository
public interface IUserSessionRepository extends ICommonRepository<User> {

    User findByUserName(String userName);
}
