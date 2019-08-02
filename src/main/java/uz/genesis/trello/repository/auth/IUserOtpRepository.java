package uz.genesis.trello.repository.auth;

import uz.genesis.trello.criterias.auth.UserOtpCriteria;
import uz.genesis.trello.domain.auth.UserOtp;
import uz.genesis.trello.repository.IGenericCrudRepository;


public interface IUserOtpRepository extends IGenericCrudRepository<UserOtp, UserOtpCriteria> {
}
