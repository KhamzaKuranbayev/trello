package uz.genesis.trello.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "AuthTry create request")
public class AuthTryCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private String userName;

    @ApiModelProperty(required = true)
    private GenericDto resultType;

    private String ipAddress;

}
