package uz.genesis.trello.dto.main;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "TaskComment Create request")
public class TaskCommentCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private Long taskId;

    @ApiModelProperty(required = true)
    private String commentText;
}
