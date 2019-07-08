package uz.genesis.trello.service.hr;

import uz.genesis.trello.criterias.hr.GroupCriteria;
import uz.genesis.trello.dto.hr.GroupCreateDto;
import uz.genesis.trello.dto.hr.GroupDto;
import uz.genesis.trello.dto.hr.GroupUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface IGroupService  extends IGenericCrudService<GroupDto, GroupCreateDto, GroupUpdateDto, Long, GroupCriteria> {
}
