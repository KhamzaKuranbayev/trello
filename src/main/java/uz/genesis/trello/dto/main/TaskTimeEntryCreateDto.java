package uz.genesis.trello.dto.main;

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
@ApiModel("TimeSheet create request")
public class TaskTimeEntryCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private Long id;

    @ApiModelProperty(required = true)
    private GenericDto entryType;

}
