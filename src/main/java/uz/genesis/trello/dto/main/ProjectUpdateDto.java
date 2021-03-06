package uz.genesis.trello.dto.main;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Project Update Request")
public class ProjectUpdateDto extends GenericCrudDto {
    @ApiModelProperty(required = true, example = "1")
    private long id;
    @ApiModelProperty(required = true)
    private String name;
    @ApiModelProperty(required = true)
    private String codeName;
    @ApiModelProperty(required = true)
    private GenericDto group;

    private GenericDto projectType;

    @ApiModelProperty(required = true)
    private Long organizationId;
}
