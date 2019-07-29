package uz.genesis.trello.domain.files;

import lombok.*;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.domain.organization.Organization;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "file_backgrounds")
public class Background extends Auditable {

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(unique = true)
    private String name;
}
