package uz.genesis.trello.dto.achievement;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.settings.TypeDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoinSettingsDto extends GenericDto {
    private TypeDto settingsType;
    private Long coins;

    @Builder(builderMethodName = "childBuilder")
    public CoinSettingsDto(Long id, TypeDto settingsType, Long coins) {
        super(id);
        this.settingsType = settingsType;
        this.coins = coins;
    }
}
