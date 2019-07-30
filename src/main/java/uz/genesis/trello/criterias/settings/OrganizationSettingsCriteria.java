package uz.genesis.trello.criterias.settings;

import lombok.*;
import uz.genesis.trello.criterias.GenericCriteria;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationSettingsCriteria extends GenericCriteria {

    private Long organizationId;
    private String settings;

    @Builder(builderMethodName = "childBuilder")
    public OrganizationSettingsCriteria(Long selfId, Integer page, Integer perPage, String sortBy, String sortDirection, Long organizationId, String settings) {
        super(selfId, page, perPage, sortBy, sortDirection);
        this.organizationId = organizationId;
        this.settings = settings;
    }
}
