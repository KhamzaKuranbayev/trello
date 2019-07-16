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
@ApiModel(value = "Task update request")
public class TaskUpdateDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private Long id;

    @ApiModelProperty(required = true)
    private Long projectId;

    @ApiModelProperty(required = true)
    private Long columnId;

    @ApiModelProperty(required = true)
    private String name;

    private Integer ordering;

    @ApiModelProperty(example = "DD-MM-YYYY HH24:MI:SS")
    private String deadLine;

    @ApiModelProperty(example = "DD-MM-YYYY HH24:MI:SS")
    private String taskAt;

    private String description;


}
