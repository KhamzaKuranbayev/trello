package uz.genesis.trello.repository.settings;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.settings.ErrorMessageCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.settings.ErrorMessage;

import java.util.List;
import java.util.Map;

@Repository
public class ErrorMessageRepository extends GenericDao<ErrorMessage, ErrorMessageCriteria> implements IErrorMessageRepository {

    @Override
    protected void defineCriteriaOnQuerying(ErrorMessageCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!utils.isEmpty(criteria.getSelfId())) {
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if (!utils.isEmpty(criteria.getErrorCode())) {
            whereCause.add("t.errorCode = :errorCode");
            params.put("errorCode", criteria.getErrorCode());
        }


        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
