package uz.genesis.trello.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.CrudDto;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("Permission update request")
public class PermissionUpdateDto implements CrudDto {
    @ApiModelProperty(required = true, example = "1")
    private Long id;

    @ApiModelProperty(required = true, example = "Write")
    private String name;

    @ApiModelProperty(required = true, example = "WRITE")
    private String codeName;
}
