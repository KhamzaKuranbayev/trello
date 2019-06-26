package uz.genesis.trello.dto.auth;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

import java.util.List;

/**
 * Created by 'javokhir' on 12/06/2019
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends GenericDto {

    private String roleName;
    private List<PermissionDto> permissions;


    @Builder(builderMethodName = "childBuilder")
    public RoleDto(Long id, String roleName, List<PermissionDto> permissions) {
        super(id);
        this.roleName = roleName;
        this.permissions = permissions;
    }
}
