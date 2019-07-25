package uz.genesis.trello.domain.achievement;

import lombok.*;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "achievement_user_income_coins")
public class UserIncomeCoin extends Auditable {

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "coins")
    private Long coins;

}
