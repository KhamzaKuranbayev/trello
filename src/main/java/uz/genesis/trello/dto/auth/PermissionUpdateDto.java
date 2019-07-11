package uz.genesis.trello.dto.auth;

import lombok.*;
import uz.genesis.trello.dto.CrudDto;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionUpdateDto implements CrudDto {
    private Long id;
    private String name;
    private String codeName;
}
