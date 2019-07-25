package uz.genesis.trello.dto.file;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BackgroundDto extends GenericDto {
    private String name;

    @Builder(builderMethodName = "childBuilder")
    public BackgroundDto(Long id, String name) {
        super(id);
        this.name = name;
    }
}
