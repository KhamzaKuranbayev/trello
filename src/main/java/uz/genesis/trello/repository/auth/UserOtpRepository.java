package uz.genesis.trello.repository.auth;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.auth.UserOtpCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.auth.UserOtp;

@Repository
public class UserOtpRepository extends GenericDao<UserOtp, UserOtpCriteria> implements IUserOtpRepository {

}
