package uz.genesis.trello.dto.achievement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Save and update coinSettings request")
public class CoinSettingsCrudDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private GenericDto settingsType;

    @ApiModelProperty(required = true)
    private Long coins;
}
