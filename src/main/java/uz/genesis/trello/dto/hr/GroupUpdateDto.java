package uz.genesis.trello.dto.hr;

import io.swagger.annotations.Api;
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
@Api(value = "Group update request Example")
public class GroupUpdateDto extends GenericCrudDto {
    @ApiModelProperty(required = true, example = "1")
    private Long id;
    private String name;
    private boolean watcher;
    private List<GenericDto> userIds;
}
