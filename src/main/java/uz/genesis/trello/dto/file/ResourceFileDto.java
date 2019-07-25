package uz.genesis.trello.dto.file;

import lombok.*;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.settings.TypeDto;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceFileDto extends GenericDto {
    private TypeDto objectType;
    private Long objectId;
    private String name;
    private String url;
    private String mimeType;
    private Long size;
    private boolean isDefault;

    @Builder(builderMethodName = "childBuilder")
    public ResourceFileDto(Long id, TypeDto objectType, Long objectId, String name, String url, String mimeType, Long size, boolean isDefault) {
        super(id);
        this.objectType = objectType;
        this.objectId = objectId;
        this.name = name;
        this.url = url;
        this.mimeType = mimeType;
        this.size = size;
        this.isDefault = isDefault;
    }
}
