package uz.genesis.trello.dto.main;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "Project Member Update request example")
public class ProjectMemberUpdateDto extends GenericCrudDto {
    @ApiModelProperty(required = true, example = "1")
    private Long id;

    @ApiModelProperty(required = true, example = "1")
    private long projectId;

    @ApiModelProperty(required = true)
    private GenericDto employee;
}
