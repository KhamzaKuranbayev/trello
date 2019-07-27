package uz.genesis.trello.dto.auth;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.settings.TypeDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthTryDto extends GenericDto {
    private String userName;
    private String ipAddress;
    private TypeDto resultType;

    @Builder(builderMethodName = "childBuilder")
    public AuthTryDto(Long id, String userName, String ipAddress, TypeDto resultType) {
        super(id);
        this.userName = userName;
        this.ipAddress = ipAddress;
        this.resultType = resultType;
    }
}
