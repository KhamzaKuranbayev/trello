package uz.genesis.trello.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "UserLastLogin create request")
public class UserLastLoginCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private Long userId;

    @ApiModelProperty(required = true)
    private String sessionToken;

    private String ipAddress;
}
