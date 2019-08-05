package uz.genesis.trello.dto.auth;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOtpDto extends GenericDto{
    private String userName;
    private GenericDto otpType;

    @Builder(builderMethodName = "childBuilder")
    public UserOtpDto(Long id, String userName, GenericDto otpType) {
        super(id);
        this.userName = userName;
        this.otpType = otpType;
    }
}
