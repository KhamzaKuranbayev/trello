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
@Table(name = "resource_files")
public class ResourceFile extends Auditable {

    @OneToOne
    @JoinColumn(name = "object_type", referencedColumnName = "id")
    private Type objectType;

    @Column(name = "object_id")
    private Long objectId;

    @Column(name = "file_name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "mime_type")
    private String mimeType;

    @Column(name = "file_size")
    private Long size;

    @Column(name = "is_default")
    private boolean isDefault;
}
