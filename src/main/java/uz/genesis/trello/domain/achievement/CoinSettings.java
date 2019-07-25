package uz.genesis.trello.domain.achievement;


import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.settings.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "achievement_coin_settings")
public class CoinSettings extends Auditable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "settings_type", nullable = false, referencedColumnName = "id")
    private Type settingsType;

    @Column(name = "coins")
    private Long coins;
}
