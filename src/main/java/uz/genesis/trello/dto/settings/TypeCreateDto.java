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
@ApiModel(value = "Type Create request")
public class TypeCreateDto implements CrudDto {
    @ApiModelProperty(required = true)
    private String name;

    @ApiModelProperty(required = true)
    private String value;
    private Integer ordering;
}
