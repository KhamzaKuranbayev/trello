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
@ApiModel(value = "Project Create request")
public class ProjectCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true, example = "name")
    private String name;
    @ApiModelProperty(required = true)
    private String codeName;
    private GenericDto group;
    @ApiModelProperty(required = true)
    private GenericDto projectType;

}
