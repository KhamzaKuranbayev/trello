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
@ApiModel(value = "FileHandout create request")
public class FileHandoutCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private GenericDto sourceType;

    @ApiModelProperty(required = true)
    private GenericDto sourceTypeParent;

    @ApiModelProperty(required = true)
    private Long sourceId;

}
