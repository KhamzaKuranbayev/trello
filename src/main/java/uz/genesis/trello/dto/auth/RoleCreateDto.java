package uz.genesis.trello.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Role create request")
public class RoleCreateDto implements CrudDto {
    @ApiModelProperty(required = true, example = "roleName")
    private String roleName;

    @ApiModelProperty(required = true, example = "codeName")
    private String codeName;

    @ApiModelProperty(required = true, example = "organizationId")
    private Long organizationId;

    private List<GenericDto> permissions;
}
