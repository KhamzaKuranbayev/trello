package uz.genesis.trello.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.CrudDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "Permission create request")
public class PermissionCreateDto implements CrudDto {
    @ApiModelProperty(required = true, example = "readALL")
    private String name;

    @ApiModelProperty(required = true, example = "READALL")
    private String codeName;

}
