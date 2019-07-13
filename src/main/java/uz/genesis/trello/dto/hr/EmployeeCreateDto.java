package uz.genesis.trello.dto.hr;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
@Api(value = "Employee create request example")
public class EmployeeCreateDto extends GenericCrudDto {

    @ApiModelProperty(required = true, example = "1")
    private Long userId;
    @ApiModelProperty(example = "DD-MM-YYYY HH24:MI:SS")
    private String birthDate;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long branchId;

    private UserCreateDto user;
}
