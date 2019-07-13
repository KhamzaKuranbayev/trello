package uz.genesis.trello.dto.auth;

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
public class RoleCreateDto implements CrudDto {
    @ApiModelProperty(required = true, example = "client")
    private String roleName;

    @ApiModelProperty(required = true, example = "CLIENT")
    private String codeName;

    private List<GenericDto> permissions;
}
