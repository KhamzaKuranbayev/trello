package uz.genesis.trello.service.auth;

import uz.genesis.trello.criterias.auth.AuthTryCriteria;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.auth.AuthTryCreateDto;
import uz.genesis.trello.dto.auth.AuthTryDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface IAuthTryService extends IGenericCrudService<AuthTryDto, AuthTryCreateDto, CrudDto, Long, AuthTryCriteria> {
}
