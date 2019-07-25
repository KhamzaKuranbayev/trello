package uz.genesis.trello.domain.files;

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
@Table(name = "file_handouts")
public class FileHandout extends Auditable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_type", referencedColumnName = "id")
    private Type sourceType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_type_parent", referencedColumnName = "id")
    private Type sourceTypeParent;

    @Column(name = "source_id")
    private Long sourceId;
}
