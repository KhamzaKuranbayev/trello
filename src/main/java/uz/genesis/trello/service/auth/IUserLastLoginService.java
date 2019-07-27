package uz.genesis.trello.service.auth;

import uz.genesis.trello.criterias.auth.UserLastLoginCriteria;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.auth.UserLastLoginCreateDto;
import uz.genesis.trello.dto.auth.UserLastLoginDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface IUserLastLoginService extends IGenericCrudService<UserLastLoginDto, UserLastLoginCreateDto, CrudDto, Long, UserLastLoginCriteria> {
}
