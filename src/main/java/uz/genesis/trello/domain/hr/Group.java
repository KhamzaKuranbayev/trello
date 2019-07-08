package uz.genesis.trello.domain.hr;

import lombok.*;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 'javokhir' on 08/07/2019
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hr_groups")
public class Group extends Auditable {

    private String name;

    @Column(name = "is_watcher")
    private boolean watcher;
}
