package uz.genesis.trello.service.auth;

import uz.genesis.trello.criterias.auth.PermissionCriteria;
import uz.genesis.trello.dto.auth.PermissionCreateDto;
import uz.genesis.trello.dto.auth.PermissionDto;
import uz.genesis.trello.dto.auth.PermissionUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface IPermissionService extends IGenericCrudService<PermissionDto, PermissionCreateDto, PermissionUpdateDto, Long, PermissionCriteria> {
}
