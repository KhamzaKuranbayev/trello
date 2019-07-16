package uz.genesis.trello.dto.main;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "TaskTag create request")
public class TaskTagCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private long projectTagId;

    @ApiModelProperty(required = true)
    private Long taskId;
}
