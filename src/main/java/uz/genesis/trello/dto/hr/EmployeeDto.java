package uz.genesis.trello.dto.hr;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.RoleDto;
import uz.genesis.trello.dto.auth.UserDto;

import java.util.Date;
import java.util.List;


/**
 * Created by 'javokhir' on 04/07/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto extends UserDto {

    private Long userId;
    private Date birthDate;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long branchId;

    @Builder(builderMethodName = "employeeBuilder")
    public EmployeeDto(String email, String userName, List<RoleDto> roles, Long userId, Date birthDate, String firstName, String middleName, String lastName, Long branchId) {
        super(email, userName, roles);
        this.userId = userId;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.branchId = branchId;
    }
}
