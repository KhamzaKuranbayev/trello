package uz.genesis.trello.criterias.achievement;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoinSettingsCriteria extends GenericCriteria {
    private Long typeId;
    private Long coins;


    @Builder(builderMethodName = "childBuilder")
    public CoinSettingsCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Long typeId, Long coins) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.typeId = typeId;
        this.coins = coins;
    }
}
