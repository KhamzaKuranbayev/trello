package uz.genesis.trello.dto.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.annotation.CustomField;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.settings.TypeDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto extends GenericDto {
    private Long projectId;
    private Long columnId;
    private String name;
    private String description;
    private String deadLine;
    private Integer ordering;
    @CustomField
    private String status;
    private String startAt;
    private TypeDto taskLevelType;
    private TypeDto taskPriorityType;

}
