package uz.genesis.trello.dto.hr;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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
@Api(value = "Employee update request example")
public class EmployeeUpdateDto implements CrudDto {

    @ApiModelProperty(required = true, example = "1")
    private Long userId;

    @ApiModelProperty(example = "DD-MM-YYYY HH24:MI:SS")
    private String birthDate;

    private String firstName;
    private String middleName;
    private String lastName;
    private Long branchId;
}
