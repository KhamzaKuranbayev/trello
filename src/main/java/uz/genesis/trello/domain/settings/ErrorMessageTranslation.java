package uz.genesis.trello.domain.settings;


import lombok.*;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "settings_error_message_translations")
public class ErrorMessageTranslation extends Auditable {

    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @OneToOne
    @JoinColumn(name = "lang_id", referencedColumnName = "id")
    private Language language;
}
