package uz.genesis.trello.domain.settings;

import lombok.*;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "settings_error_messages")
public class ErrorMessage extends Auditable {

    @Column(name = "error_code")
    private String errorCode;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "message_id")
    private List<ErrorMessageTranslation> translations = new ArrayList<>();
}
