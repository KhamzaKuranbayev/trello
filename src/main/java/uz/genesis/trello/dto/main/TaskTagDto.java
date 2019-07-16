package uz.genesis.trello.dto.main;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TaskTagDto extends GenericDto {
    private Long projectTagId;
    private Long taskId;

}
