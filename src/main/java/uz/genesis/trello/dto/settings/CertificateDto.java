package uz.genesis.trello.dto.settings;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Setting certificate request")
public class CertificateDto {
    @ApiModelProperty(required = true)
    private String privateKey;
}
