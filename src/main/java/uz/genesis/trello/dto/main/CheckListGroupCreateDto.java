package uz.genesis.trello.dto.main;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericCrudDto;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ChackListGroup create request")
public class CheckListGroupCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true)
    private String name;

    @ApiModelProperty(required = true)
    private Long taskId;

}
