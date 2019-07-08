package uz.genesis.trello.dto.hr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.auth.UserCreateDto;

/**
 * Created by 'javokhir' on 04/07/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreateDto extends GenericCrudDto {

    private Long userId;
    private String birthDate;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long branchId;

    private UserCreateDto user;
}
