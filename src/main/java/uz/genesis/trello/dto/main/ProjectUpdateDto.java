package uz.genesis.trello.dto.main;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;

import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Api(value = "Project Update Request example")
public class ProjectUpdateDto extends GenericCrudDto {
    @ApiModelProperty(required = true, example = "1")
    private long id;
    @ApiModelProperty(required = true)
    private String name;
    private GenericDto group;
    @ApiModelProperty(required = true)
    private GenericDto projectType;
}
