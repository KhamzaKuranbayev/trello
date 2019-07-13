package uz.genesis.trello.dto.settings;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Api
@ApiModel(description = "creating for sub type", value = "SubType create request")
public class SubTypeCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private String name;
    @ApiModelProperty(required = true)
    private String value;
    private Integer ordering;
    @ApiModelProperty(required = true)
    private String typeCode;
}
