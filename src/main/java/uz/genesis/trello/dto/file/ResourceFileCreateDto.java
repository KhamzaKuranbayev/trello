package uz.genesis.trello.dto.file;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Resource file create request")
public class ResourceFileCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private GenericDto objectType;

    @ApiModelProperty(required = true)
    private Long objectId;
    private String name;

    @ApiModelProperty(required = true)
    private String url;

    private String mimeType;
    private Long size;
    private boolean isDefault;


}
