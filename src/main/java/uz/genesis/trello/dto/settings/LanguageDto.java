package uz.genesis.trello.dto.settings;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanguageDto extends GenericDto {
    private String code;
    private String name;

    @Builder(builderMethodName = "childBuilder")
    public LanguageDto(Long id, String code, String name) {
        super(id);
        this.code = code;
        this.name = name;
    }
}
