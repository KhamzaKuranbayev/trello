package uz.genesis.trello.repository.organization;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.organization.OrganizationCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.organization.Organization;

import java.util.List;
import java.util.Map;

@Repository
public class OrganizationRepository extends GenericDao<Organization, OrganizationCriteria> implements IOrganizationRepository {
    @Override
    protected void defineCriteriaOnQuerying(OrganizationCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!utils.isEmpty(criteria.getEmail())) {
            whereCause.add("t.email = :email");
            params.put("email", criteria.getEmail());
        }
        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
