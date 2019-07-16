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
@ApiModel(value = "Task create request")
public class TaskCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private Long projectId;

    @ApiModelProperty(required = true)
    private Long columnId;

    @ApiModelProperty(required = true)
    private String name;

}
