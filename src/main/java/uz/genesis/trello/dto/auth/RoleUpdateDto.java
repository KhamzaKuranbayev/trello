package uz.genesis.trello.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "Role update request")
public class RoleUpdateDto implements CrudDto {
    @ApiModelProperty(required = true, example = "15")
    private Long id;

    @ApiModelProperty(required = true, example = "Client")
    private String roleName;

    @ApiModelProperty(required = true, example = "CLIENT")
    private String codeName;

    private List<GenericDto> permissions;

}
