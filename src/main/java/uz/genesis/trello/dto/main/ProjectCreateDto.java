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
@Builder
@Api(value = "Project Create request example")
public class ProjectCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true, example = "name")
    private String name;

    private GenericDto group;
    private GenericDto projectType;

}
