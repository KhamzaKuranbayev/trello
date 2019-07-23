package uz.genesis.trello.service.main;

import uz.genesis.trello.criterias.main.TaskCheckListCriteria;
import uz.genesis.trello.domain.main.TaskCheckList;
import uz.genesis.trello.dto.main.CheckListCountDto;
import uz.genesis.trello.dto.main.TaskCheckListCreateDto;
import uz.genesis.trello.dto.main.TaskCheckListDto;
import uz.genesis.trello.dto.main.TaskCheckListUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface ITaskCheckListService  extends IGenericCrudService<TaskCheckListDto, TaskCheckListCreateDto, TaskCheckListUpdateDto, Long, TaskCheckListCriteria> {
    CheckListCountDto getCheckListCount(TaskCheckListCriteria criteria);
}
