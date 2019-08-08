package uz.genesis.trello.dto.organization;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationUserDto extends GenericCrudDto {
    @ApiModelProperty(required = true, example = "1")
    private Long id;
    private String organizationEmail;
    private String userName;
    private String prefix;
    private String name;
    private String lastName;
    private String firstName;
    private String middleName;
    private String email;
    private String phoneNumber;

    @Builder(builderMethodName = "childBuilder")
    public OrganizationUserDto(String organizationEmail, String prefix, String name, String lastName, String firstName, String middleName, String email, String phoneNumber) {
        this.organizationEmail = organizationEmail;
        this.prefix = prefix;
        this.name = name;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
