package uz.genesis.trello.dto.main;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "TaskMember create request")
public class TaskMemberCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private Long taskId;

    @ApiModelProperty(required = true)
    private GenericDto employee;


}
