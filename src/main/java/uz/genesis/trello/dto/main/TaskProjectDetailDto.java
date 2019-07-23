package uz.genesis.trello.dto.main;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskProjectDetailDto extends GenericDto {
    private Long projectId;
    private Long columnId;
    private String name;
    private String description;
    private String deadLine;
    private Integer ordering;
    private String startAt;
    private List<TaskTagDto> tagList;
    private List<TaskMemberDto> members;
    private CheckListCountDto checkListCount;
    private Long commentCount;

    @Builder(builderMethodName = "childBuilder")
    public TaskProjectDetailDto(Long id, Long projectId, Long columnId, String name, String description, String deadLine, Integer ordering, String startAt, CheckListCountDto checkListCount, Long commentCount, List<TaskMemberDto> members) {
        super(id);
        this.projectId = projectId;
        this.columnId = columnId;
        this.name = name;
        this.description = description;
        this.deadLine = deadLine;
        this.ordering = ordering;
        this.startAt = startAt;
        this.members = members;
        this.checkListCount = checkListCount;
        this.commentCount = commentCount;
    }
}
