package uz.genesis.trello.dto.main;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskCheckListDto extends GenericDto {
    private Long taskId;
    private boolean checked;
    private String text;
    private List<CheckListMemberDto> members;


}
