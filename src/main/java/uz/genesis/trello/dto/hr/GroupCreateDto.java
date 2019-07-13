package uz.genesis.trello.dto.hr;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.GenericDto;

import java.util.List;

/**
 * Created by 'javokhir' on 08/07/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Group Create request")
public class GroupCreateDto extends GenericCrudDto {
    @ApiModelProperty(required = true, example = "group")
    private String name;

    private boolean watcher;
    private List<GenericDto> userIds;
}
