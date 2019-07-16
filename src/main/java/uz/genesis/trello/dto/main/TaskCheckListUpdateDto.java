package uz.genesis.trello.dto.main;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "TaskCheckList update request")
public class TaskCheckListUpdateDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private Long id;

    @ApiModelProperty(required = true)
    private Long taskId;

    @ApiModelProperty(required = true)
    private String text;

    @ApiModelProperty(example = "false")
    private boolean checked;

    private List<GenericDto> members;
}
