package uz.genesis.trello.service.settings;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.settings.OrganizationSettingsCriteria;
import uz.genesis.trello.dao.FunctionParam;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.dto.settings.CertificateDto;
import uz.genesis.trello.dto.settings.OrganizationSettingsCreateDto;
import uz.genesis.trello.dto.settings.OrganizationSettingsDto;
import uz.genesis.trello.dto.settings.OrganizationSettingsUpdateDto;
import uz.genesis.trello.mapper.settings.OrganizationSettingsMapper;
import uz.genesis.trello.repository.settings.IOrganizationSettingsRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;
import uz.genesis.trello.utils.UserSession;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationSettingsService extends AbstractCrudService<OrganizationSettingsDto, OrganizationSettingsCreateDto, OrganizationSettingsUpdateDto, OrganizationSettingsCriteria, IOrganizationSettingsRepository> implements IOrganizationSettingsService {
    private final OrganizationSettingsMapper mapper;
    private final UserSession userSession;
    private final ObjectMapper objectMapper;


    public OrganizationSettingsService(IOrganizationSettingsRepository repository, BaseUtils utils, OrganizationSettingsMapper mapper, UserSession userSession, ObjectMapper objectMapper) {
        super(repository, utils);
        this.mapper = mapper;
        this.userSession = userSession;
        this.objectMapper = objectMapper;
    }

    @Override
    public OrganizationSettingsDto getOrganizationSettings(OrganizationSettingsCriteria criteria) {
        return mapper.toDto(repository.find(criteria));
    }

    @Override
    public Integer getCurrentUserCount(Long organizationId) {
        List<FunctionParam> params = new ArrayList<>();
        params.add(new FunctionParam(organizationId, Types.BIGINT));

        return repository.call(params, "getsessioncountbyorganization", Types.INTEGER);
    }

    @Override
    public ResponseEntity<DataDto<Boolean>> setCertificate(CertificateDto certificateDto) {
        ObjectNode settingsNode = objectMapper.createObjectNode();
        settingsNode.put("certificate", certificateDto.getPrivateKey());

        OrganizationSettingsCreateDto dto = OrganizationSettingsCreateDto.builder().
                organizationId(userSession.getUser().getOrganizationId()).
                settings(settingsNode.toString()).build();
        boolean success = repository.call(dto, "setCertificate", Types.BOOLEAN);
        return new ResponseEntity<>(new DataDto<>(success), HttpStatus.OK);
    }


}

