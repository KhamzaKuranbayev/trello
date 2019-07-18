package uz.genesis.trello.service.main;

import uz.genesis.trello.criterias.main.CheckListGroupCriteria;
import uz.genesis.trello.dto.main.CheckListGroupCreateDto;
import uz.genesis.trello.dto.main.CheckListGroupDto;
import uz.genesis.trello.dto.main.CheckListGroupUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface ICheckListGroupService extends IGenericCrudService<CheckListGroupDto, CheckListGroupCreateDto, CheckListGroupUpdateDto, Long, CheckListGroupCriteria> {
}
