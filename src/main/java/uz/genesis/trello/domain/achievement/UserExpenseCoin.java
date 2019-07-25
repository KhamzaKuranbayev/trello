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
@Table(name = "achievement_user_expense_coins")
public class UserExpenseCoin extends Auditable {

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "expense_type", nullable = false, referencedColumnName = "id")
    private Type expenseType;

    @Column(name = "expense_reason", columnDefinition = "TEXT")
    private String expenseReason;
}
