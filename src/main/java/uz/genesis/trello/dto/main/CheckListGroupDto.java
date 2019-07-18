package uz.genesis.trello.dto.main;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckListGroupDto extends GenericDto {
    private String name;
    private Long taskId;
    private List<TaskCheckListDto> checkList;

    @Builder(builderMethodName = "childBuilder")
    public CheckListGroupDto(Long id, String name, Long taskId, List<TaskCheckListDto> checkList) {
        super(id);
        this.name = name;
        this.taskId = taskId;
        this.checkList = checkList;
    }
}
