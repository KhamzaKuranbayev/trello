package uz.genesis.trello.service.main;

import uz.genesis.trello.criterias.main.TaskTagCriteria;
import uz.genesis.trello.dto.main.TaskTagCreateDto;
import uz.genesis.trello.dto.main.TaskTagDto;
import uz.genesis.trello.dto.main.TaskTagUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface ITaskTagService extends IGenericCrudService<TaskTagDto, TaskTagCreateDto, TaskTagUpdateDto, Long, TaskTagCriteria> {
}
