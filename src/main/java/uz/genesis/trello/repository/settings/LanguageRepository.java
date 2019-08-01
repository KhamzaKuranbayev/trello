package uz.genesis.trello.repository.settings;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.settings.LanguageCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.settings.Language;

import java.util.List;
import java.util.Map;

@Repository
public class LanguageRepository extends GenericDao<Language, LanguageCriteria> implements ILanguageRepository {
    @Override
    protected void defineCriteriaOnQuerying(LanguageCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getSelfId())){
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if(!utils.isEmpty(criteria.getCode())){
            whereCause.add("t.code = :code");
            params.put("code", criteria.getCode());
        }
        if(!utils.isEmpty(criteria.getName())){
            whereCause.add("t.name = :name");
            params.put("name", criteria.getName());
        }


        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
