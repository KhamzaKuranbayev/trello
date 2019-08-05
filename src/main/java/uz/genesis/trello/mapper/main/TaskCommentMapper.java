package uz.genesis.trello.mapper.main;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;
import uz.genesis.trello.domain.main.TaskComment;
import uz.genesis.trello.dto.main.TaskCommentCreateDto;
import uz.genesis.trello.dto.main.TaskCommentDto;
import uz.genesis.trello.dto.main.TaskCommentUpdateDto;
import uz.genesis.trello.mapper.BaseMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface TaskCommentMapper extends BaseMapper<TaskComment, TaskCommentDto, TaskCommentCreateDto, TaskCommentUpdateDto> {
}
