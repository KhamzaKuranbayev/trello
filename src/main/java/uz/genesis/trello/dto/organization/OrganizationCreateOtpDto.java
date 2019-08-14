package uz.genesis.trello.dto.organization;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationCreateOtpDto extends GenericDto {
    private String email;

    @Builder(builderMethodName = "childBuilder")
    public OrganizationCreateOtpDto(Long id, String email) {
        super(id);
        this.email = email;
    }
}
