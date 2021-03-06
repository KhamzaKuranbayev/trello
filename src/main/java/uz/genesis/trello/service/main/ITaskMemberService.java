package uz.genesis.trello.service.main;

import uz.genesis.trello.criterias.main.TaskMemberCriteria;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.main.TaskMemberCreateDto;
import uz.genesis.trello.dto.main.TaskMemberDto;
import uz.genesis.trello.service.IGenericCrudService;

import java.util.List;

public interface ITaskMemberService extends IGenericCrudService<TaskMemberDto, TaskMemberCreateDto, CrudDto, Long, TaskMemberCriteria> {
    List<TaskMemberDto> getAllTaskMemberList(TaskMemberCriteria criteria);
}
