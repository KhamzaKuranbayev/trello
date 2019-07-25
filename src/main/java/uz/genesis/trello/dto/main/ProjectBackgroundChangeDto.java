package uz.genesis.trello.dto.main;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Changing project background request")
public class ProjectBackgroundChangeDto {
    @ApiModelProperty(required = true)
    private String background;

    @ApiModelProperty(required = true)
    private Long projectId;
}
