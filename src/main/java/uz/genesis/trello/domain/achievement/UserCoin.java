package uz.genesis.trello.domain.achievement;

import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.auth.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "achievement_user_coins")
public class UserCoin extends Auditable {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "coins")
    private Long coins;
}
