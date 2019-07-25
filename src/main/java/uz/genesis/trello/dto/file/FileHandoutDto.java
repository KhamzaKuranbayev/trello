package uz.genesis.trello.dto.file;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.settings.TypeDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileHandoutDto extends GenericDto {
    private TypeDto sourceType;
    private TypeDto sourceTypeParent;
    private Long sourceId;

    @Builder(builderMethodName = "childBuilder")
    public FileHandoutDto(Long id, TypeDto sourceType, TypeDto sourceTypeParent, Long sourceId) {
        super(id);
        this.sourceType = sourceType;
        this.sourceTypeParent = sourceTypeParent;
        this.sourceId = sourceId;
    }
}
