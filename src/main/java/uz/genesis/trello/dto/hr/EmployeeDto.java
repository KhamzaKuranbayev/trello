package uz.genesis.trello.dto.hr;

import lombok.*;
import org.mapstruct.factory.Mappers;
import uz.genesis.trello.domain.auth.Role;
import uz.genesis.trello.domain.hr.Employee;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.auth.RoleDto;
import uz.genesis.trello.dto.auth.UserDto;
import uz.genesis.trello.mapper.auth.RoleMapper;

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
    private String photoUrl;

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

    public EmployeeDto(Employee employee, String photoUrl) {
        RoleMapper  mapper = Mappers.getMapper(RoleMapper.class);
        setEmail(employee.getEmail());
        this.birthDate = employee.getBirthDate();
        this.userId = employee.getUserId();
        this.firstName = employee.getFirstName();
        this.middleName = employee.getMiddleName();
        this.lastName = employee.getLastName();
        this.branchId = employee.getBranchId();
        this.photoUrl = photoUrl;
        setUserName(employee.getUserName());
        setRoles( mapper.toDto((List<Role>) employee.getRoles()));
    }
}
