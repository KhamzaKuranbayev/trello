package uz.genesis.trello.dto.hr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.CrudDto;

import java.sql.Date;

/**
 * Created by 'javokhir' on 04/07/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateDto implements CrudDto {

    private Long id;
    private Long userId;
    private Date birthDate;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long branchId;
}
