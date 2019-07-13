package uz.genesis.trello.domain.settings;

import lombok.*;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by 'javokhir' on 28/06/2019
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "settings_types")
public class Type extends Auditable {

    private String name;

    @Column(name = "type_code")
    private String typeCode;

    @Column(unique = true)
    private String value;

    @Column(name = "ordering", columnDefinition = "NUMERIC default null")
    private Integer ordering;
}
