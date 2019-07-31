package uz.genesis.trello.dto.settings;

import io.swagger.annotations.ApiModel;
import lombok.*;
import uz.genesis.trello.dto.GenericCrudDto;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("OrganizationSettings create request")
@Builder
public class OrganizationSettingsCreateDto extends GenericCrudDto {
    private Long organizationId;
    private String settings;
}
