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
public class UserDto extends GenericDto {

    private String email;
    private String userName;
    private List<RoleDto> roles;

    @Builder(builderMethodName = "childBuilder")
    public UserDto(Long id, String email, String userName, String password, List<RoleDto> roles) {
        super(id);
        this.email = email;
        this.userName = userName;
        this.roles = roles;
    }
}
