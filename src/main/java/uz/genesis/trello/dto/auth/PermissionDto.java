package uz.genesis.trello.dto.auth;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

/**
 * Created by 'javokhir' on 12/06/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDto extends GenericDto {

    private String name;
    private String codeName;

    @Builder(builderMethodName = "childBuilder")
    public PermissionDto(Long id, String name, String codeName) {
        super(id);
        this.name = name;
        this.codeName = codeName;
    }
}
