package uz.genesis.trello.dto.main;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "Project Member create example")
public class ProjectMemberCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true, example = "1")
    private long projectId;
    @ApiModelProperty(required = true)
    private GenericDto employee;
}
