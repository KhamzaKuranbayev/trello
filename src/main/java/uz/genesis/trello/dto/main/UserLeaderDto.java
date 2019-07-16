package uz.genesis.trello.dto.main;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericDto;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("User adding while creating group request")
public class UserLeaderDto extends GenericDto {
    @ApiModelProperty(example = "false")
    private boolean leader;
}
