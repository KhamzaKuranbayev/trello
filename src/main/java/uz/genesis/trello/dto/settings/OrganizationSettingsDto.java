package uz.genesis.trello.dto.settings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import uz.genesis.trello.dto.GenericDto;

import java.io.IOException;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationSettingsDto extends GenericDto {
    private Long organizationId;
    private String settings;

    @JsonIgnoreProperties
    private JsonNode params;


    @Builder(builderMethodName = "childBuilder")
    public OrganizationSettingsDto(Long id, Long organizationId, String settings) {
        super(id);
        this.organizationId = organizationId;
        this.settings = settings;

    }

    private JsonNode getParam() {
        try {
            return new ObjectMapper().readTree(this.getSettings());
        } catch (IOException e) {
            throw new RuntimeException("Could not convert settings");
        }
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
        this.params = getParam();
    }
}
