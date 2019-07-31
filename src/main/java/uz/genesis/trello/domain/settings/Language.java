package uz.genesis.trello.domain.settings;

import lombok.*;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.*;

/**
 * Created by 'Javokhir Mamadiyarov Uygunovich' on 10/5/18.
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "setting_languages")
public class Language extends Auditable {

    @Column(name = "name")
    protected String name;

    @Column(name = "code")
    protected String code;

}
