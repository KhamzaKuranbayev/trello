package uz.genesis.trello.dto.main;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TaskCommentDto extends GenericDto {
    private Long taskId;
    private String commentText;
}
