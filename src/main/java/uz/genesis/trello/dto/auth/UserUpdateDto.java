package uz.genesis.trello.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.CrudDto;

import java.util.List;

/**
 * Created by 'javokhir' on 26/06/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto implements CrudDto {

    private String email;
    private String userName;
    private String password;
    private List<RoleDto> roles;
}
