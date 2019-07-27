package uz.genesis.trello.domain.auth;

import lombok.*;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "auth_blocking_period")
public class BlockingPeriod extends Auditable {

    private int period;
}
