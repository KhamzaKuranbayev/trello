package uz.genesis.trello.dto.main;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Api(value = "ProjectColumn Create request")
public class ProjectColumnCreateDto implements CrudDto {
    @ApiModelProperty(required = true)
    private String name;

    @ApiModelProperty(required = true)
    private String codeName;

    @ApiModelProperty(required = true)
    private Long projectId;

    @ApiModelProperty(required = true)
    private GenericDto columnType;

    private Integer ordering;

}
