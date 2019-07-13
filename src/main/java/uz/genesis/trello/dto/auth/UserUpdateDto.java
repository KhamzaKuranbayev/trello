package uz.genesis.trello.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;

import java.util.List;

/**
 * Created by 'javokhir' on 26/06/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("User update request")
public class UserUpdateDto implements CrudDto {

    @ApiModelProperty(required = true, example = "1")
    private Long id;

    @ApiModelProperty(example = "test@gmail.com")
    private String email;

    @ApiModelProperty(example = "username", required = true)
    private String userName;

    @ApiModelProperty(example = "password", required = true)
    private String password;

    private List<GenericDto> roles;
}
