package uz.genesis.trello.dto.main;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Project Create request")
public class ProjectCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true, example = "name")
    private String name;
    @ApiModelProperty(required = true)
    private String codeName;
    @ApiModelProperty(required = true)
    private GenericDto group;
    @ApiModelProperty(required = true)
    private GenericDto projectType;
    @ApiModelProperty(required = true)
    private GenericDto manager;
    @ApiModelProperty(required = true)
    private Long organizationId;
}
