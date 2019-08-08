package uz.genesis.trello.dto.organization;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationOtpConfirmDto extends GenericDto {
    private String email;
    private String otpCode;

    @Builder(builderMethodName = "childBuilder")
    public OrganizationOtpConfirmDto(Long id, String email, String otpCode) {
        super(id);
        this.email = email;
        this.otpCode = otpCode;
    }
}
