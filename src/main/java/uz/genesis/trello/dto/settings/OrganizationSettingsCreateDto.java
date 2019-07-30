package uz.genesis.trello.dto.settings;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericCrudDto;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("OrganizationSettings create request")
public class OrganizationSettingsCreateDto extends GenericCrudDto {
    private Long organizatioId;
    private String settings;
}
