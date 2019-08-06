package uz.genesis.trello.service.main;

import uz.genesis.trello.criterias.main.TaskCommentCriteria;
import uz.genesis.trello.dto.main.TaskCommentCreateDto;
import uz.genesis.trello.dto.main.TaskCommentDto;
import uz.genesis.trello.dto.main.TaskCommentUpdateDto;
import uz.genesis.trello.service.IGenericCrudService;

import java.util.List;

public interface ITaskCommentService extends IGenericCrudService<TaskCommentDto, TaskCommentCreateDto, TaskCommentUpdateDto, Long, TaskCommentCriteria> {
    Long getCommentCount(TaskCommentCriteria criteria);

    List<TaskCommentDto> getAllTaskCommentList(TaskCommentCriteria criteria);
}
