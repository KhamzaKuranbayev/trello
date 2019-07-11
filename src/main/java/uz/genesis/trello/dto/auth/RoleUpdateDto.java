package uz.genesis.trello.dto.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.CrudDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoleUpdateDto implements CrudDto {
    private Long id;
    private String roleName;
    private String codeName;
    private List<PermissionDto> permissions;

}
