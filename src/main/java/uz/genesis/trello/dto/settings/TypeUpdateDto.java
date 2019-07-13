package uz.genesis.trello.dto.settings;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.genesis.trello.dto.CrudDto;

/**
 * Created by 'javokhir' on 01/07/2019
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Type update request")
public class TypeUpdateDto implements CrudDto {
    @ApiModelProperty(required = true)
    private Long id;

    @ApiModelProperty(required = true)
    private String name;

    private Integer ordering;

    @ApiModelProperty(required = true)
    private String value;
}
