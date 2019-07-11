package uz.genesis.trello.dto.auth;

import lombok.*;
import uz.genesis.trello.dto.CrudDto;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionCreateDto implements CrudDto {
    private String name;
    private String codeName;

}
