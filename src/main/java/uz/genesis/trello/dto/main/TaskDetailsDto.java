package uz.genesis.trello.dto.main;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.settings.TypeDto;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDetailsDto extends GenericDto {

    private Long projectId;
    private Long columnId;
    private String name;
    private String description;
    private LocalDateTime startAt;
    private LocalDateTime deadLine;
    private Integer ordering;
    private List<TaskTagDto> tags;
    private List<TaskMemberDto> members;
    private List<CheckListGroupDto> checkListGroups;
    private List<TaskCommentDto> comments;
    private TypeDto levelType;
    private TypeDto priorityType;

    @Builder(builderMethodName = "childBuilder")
    public TaskDetailsDto(Long id, Long projectId, Long columnId, String name, String description, LocalDateTime startAt, LocalDateTime deadLine, Integer ordering, List<TaskTagDto> tags, List<TaskMemberDto> members, List<CheckListGroupDto> checkListGroups, List<TaskCommentDto> comments, TypeDto levelType, TypeDto priorityType) {
        super(id);
        this.projectId = projectId;
        this.columnId = columnId;
        this.name = name;
        this.description = description;
        this.startAt = startAt;
        this.deadLine = deadLine;
        this.ordering = ordering;
        this.tags = tags;
        this.members = members;
        this.checkListGroups = checkListGroups;
        this.comments = comments;
        this.levelType = levelType;
        this.priorityType = priorityType;
    }
}
