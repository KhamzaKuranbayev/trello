package uz.genesis.trello.service.auth;

import uz.genesis.trello.criterias.auth.UserCriteria;
import uz.genesis.trello.dto.auth.UserDto;
import uz.genesis.trello.service.IGenericCrudService;

/**
 * Created by 'javokhir' on 12/06/2019
 */

public interface IUserService extends IGenericCrudService<UserDto, Long, UserCriteria> {
}
