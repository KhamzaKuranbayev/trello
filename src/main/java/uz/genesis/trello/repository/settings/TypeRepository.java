package uz.genesis.trello.repository.settings;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.settings.TypeCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.settings.Type;

import java.util.List;
import java.util.Map;

/**
 * Created by 'javokhir' on 01/07/2019
 */

@Repository
public class TypeRepository extends GenericDao<Type, TypeCriteria> implements ITypeRepository {

    @Override
    protected void defineCriteriaOnQuerying(TypeCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {

        if (utils.isEmpty(criteria.getTypeCode())) {
            whereCause.add("t.typeCode is null");
        } else {
            whereCause.add("t.typeCode = :typeCode");
            params.put("typeCode", criteria.getTypeCode().trim());
        }

        if (!utils.isEmpty(criteria.getValue())) {
            whereCause.add("t.value = :typeValue");
            params.put("typeValue", criteria.getValue().trim());
        }

        if (!utils.isEmpty(criteria.getName())) {
            whereCause.add("lower(t.name) LIKE lower('%" + criteria.getName().trim() + "%')");
        }


        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
