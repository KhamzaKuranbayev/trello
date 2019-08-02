package uz.genesis.trello.dto.auth;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOtpConfirmDto extends GenericDto {
    private Long userId;
    private String otpCode;

    @Builder(builderMethodName = "childBuilder")
    public UserOtpConfirmDto(Long id, Long userId, String otpCode) {
        super(id);
        this.userId = userId;
        this.otpCode = otpCode;
    }
}
