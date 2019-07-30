package uz.genesis.trello.domain.settings;

import lombok.*;
import uz.genesis.trello.domain.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "settings_organizations")
public class OrganizationSettings extends Auditable {

    @Column(name = "organization_id")
    private Long organizationId;

    @Column(columnDefinition = "TEXT DEFAULT NULL")
    private String settings;
}
