package uz.genesis.trello.dto.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericCrudDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationSettingsUpdateDto extends GenericCrudDto {
    private Long id;
    private Long organizatioId;
    private String settings;
}
