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
@ApiModel(value = "ProjectTag create request")
public class ProjectTagCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private Long projectId;

    @ApiModelProperty(required = true)
    private String name;
    private String color;
}
