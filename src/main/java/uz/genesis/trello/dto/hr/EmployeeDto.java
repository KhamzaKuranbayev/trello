package uz.genesis.trello.dto.hr;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

import java.sql.Date;

/**
 * Created by 'javokhir' on 04/07/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto extends GenericDto {

    private Long userId;
    private Date birthDate;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long branchId;

    @Builder(builderMethodName = "childBuilder")
    public EmployeeDto(Long id, Long userId, Date birthDate, String firstName, String middleName, String lastName, Long branchId) {
        super(id);
        this.userId = userId;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.branchId = branchId;
    }
}
