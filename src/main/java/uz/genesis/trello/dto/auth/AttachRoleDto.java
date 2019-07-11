package uz.genesis.trello.dto.auth;

import lombok.*;
import uz.genesis.trello.domain.auth.Role;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class AttachRoleDto {
    private Long id;
    private List<Role> roles;

    @Builder(builderMethodName = "childBuilder")
    public AttachRoleDto(Long id, List<Role> roles) {
        this.id = id;
        this.roles = roles;
    }
}
