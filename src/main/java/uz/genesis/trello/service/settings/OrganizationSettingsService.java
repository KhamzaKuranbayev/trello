package uz.genesis.trello.service.settings;

import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.settings.OrganizationSettingsCriteria;
import uz.genesis.trello.dao.FunctionParam;
import uz.genesis.trello.dto.settings.OrganizationSettingsCreateDto;
import uz.genesis.trello.dto.settings.OrganizationSettingsDto;
import uz.genesis.trello.dto.settings.OrganizationSettingsUpdateDto;
import uz.genesis.trello.mapper.settings.OrganizationSettingsMapper;
import uz.genesis.trello.repository.settings.IOrganizationSettingsRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationSettingsService extends AbstractCrudService<OrganizationSettingsDto, OrganizationSettingsCreateDto, OrganizationSettingsUpdateDto, OrganizationSettingsCriteria, IOrganizationSettingsRepository> implements IOrganizationSettingsService {
    private final OrganizationSettingsMapper mapper;


    public OrganizationSettingsService(IOrganizationSettingsRepository repository, BaseUtils utils, OrganizationSettingsMapper mapper) {
        super(repository, utils);
        this.mapper = mapper;
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
}

