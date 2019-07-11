package uz.genesis.trello.dto.auth;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class AttachRoleDto {
    private Long userId;
    private List<GenericDto> roles;

    @Builder(builderMethodName = "childBuilder")
    public AttachRoleDto(Long userId, List<GenericDto> roles) {
        this.userId = userId;
        this.roles = roles;
    }
}
