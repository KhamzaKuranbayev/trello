package uz.genesis.trello.dto.settings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageTranslationDto extends GenericDto {
    private Long messageId;
    private String name;
    private LanguageDto language;
}
