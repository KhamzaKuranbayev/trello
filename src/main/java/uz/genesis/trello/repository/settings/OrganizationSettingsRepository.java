package uz.genesis.trello.repository.settings;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.settings.OrganizationSettingsCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.settings.OrganizationSettings;

import java.util.List;
import java.util.Map;

@Repository
public class OrganizationSettingsRepository extends GenericDao<OrganizationSettings, OrganizationSettingsCriteria>
        implements IOrganizationSettingsRepository {

    @Override
    protected void defineCriteriaOnQuerying(OrganizationSettingsCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getSelfId())){
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }

        if (!utils.isEmpty(criteria.getOrganizationId())) {
            whereCause.add("t.organizationId = :organizationId");
            params.put("organizationId", criteria.getOrganizationId());
        }

        if (!utils.isEmpty(criteria.getSettings())) {
            whereCause.add("t.settings = :settings");
            params.put("settings", criteria.getSettings());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
