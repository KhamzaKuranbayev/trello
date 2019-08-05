package uz.genesis.trello.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.settings.LanguageDto;

/**
 * Created by 'javokhir' on 26/06/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "User create request")
public class UserCreateDto implements CrudDto {
    @ApiModelProperty(example = "simple@gmail.com")
    private String email;
    @ApiModelProperty(required = true, example = "userName")
    private String userName;
    @ApiModelProperty(required = true, example = "password")
    private String password;
    @ApiModelProperty(required = true, example = "language")
    private GenericDto language;
}
