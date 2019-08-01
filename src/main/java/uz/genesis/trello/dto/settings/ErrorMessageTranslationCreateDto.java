package uz.genesis.trello.dto.settings;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorMessageTranslationCreateDto {
    private String name;
    private String langCode;

}
