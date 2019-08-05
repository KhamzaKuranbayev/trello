package uz.genesis.trello.dto.auth;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOtpConfirmDto extends GenericDto {
    private String username;
    private String otpCode;

    @Builder(builderMethodName = "childBuilder")
    public UserOtpConfirmDto(Long id, String username, String otpCode) {
        super(id);
        this.username = username;
        this.otpCode = otpCode;
    }
}
