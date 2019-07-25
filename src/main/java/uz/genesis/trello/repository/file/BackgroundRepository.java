package uz.genesis.trello.repository.file;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.file.BackgroundCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.files.Background;

import java.util.List;
import java.util.Map;

@Repository
public class BackgroundRepository extends GenericDao<Background, BackgroundCriteria> implements IBackgrounRepository {
    @Override
    protected void defineCriteriaOnQuerying(BackgroundCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!utils.isEmpty(criteria.getSelfId())) {
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if (!utils.isEmpty(criteria.getName())) {
            whereCause.add("t.name = : name");
            params.put("name", criteria.getName());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
