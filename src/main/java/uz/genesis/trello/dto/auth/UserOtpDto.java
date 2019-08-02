package uz.genesis.trello.dto.auth;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOtpDto extends GenericDto {
    private Long userId;
    private GenericDto otpType;

    @Builder(builderMethodName = "childBuilder")
    public UserOtpDto(Long id, Long userId, GenericDto otpType) {
        super(id);
        this.userId = userId;
        this.otpType = otpType;
    }
}
