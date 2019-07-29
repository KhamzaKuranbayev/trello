package uz.genesis.trello.domain.auth;

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
@Table(name = "auth_unblocking_period")
public class UnBlockingPeriod extends Auditable {

    @Column(columnDefinition = "integer DEFAULT 10")
    private int period;
}
