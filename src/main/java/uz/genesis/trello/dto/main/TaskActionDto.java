package uz.genesis.trello.dto.main;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.settings.TypeDto;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskActionDto extends GenericDto {
    private Long projectId;
    private Long projectColumnId;
    private Long taskId;
    private String name;
    private TypeDto actionType;
    private TypeDto actionCategoryType;
    private TypeDto activityType;
    private String textParams;


}
