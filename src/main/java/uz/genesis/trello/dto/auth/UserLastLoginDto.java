package uz.genesis.trello.dto.auth;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLastLoginDto extends GenericDto {
    private Long userId;
    private String sessionToken;
    private String logAt;
    private String ipAddress;

    @Builder(builderMethodName = "childBuilder")
    public UserLastLoginDto(Long id, Long userId, String sessionToken, String logAt, String ipAddress) {
        super(id);
        this.userId = userId;
        this.sessionToken = sessionToken;
        this.logAt = logAt;
        this.ipAddress = ipAddress;
    }
}
