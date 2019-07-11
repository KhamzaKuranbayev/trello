package uz.genesis.trello.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.CrudDto;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreateDto implements CrudDto {
    private String roleName;
    private String codeName;
    private List<PermissionDto> permissions;
}
