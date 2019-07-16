package uz.genesis.trello.repository.main;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.main.CheckListMemberCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.main.CheckListMember;

import java.util.List;
import java.util.Map;

@Repository
public class CheckListMemberRepository extends GenericDao<CheckListMember, CheckListMemberCriteria> implements ICheckListMemberRepository {
    @Override
    protected void defineCriteriaOnQuerying(CheckListMemberCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getSelfId())){
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if(!utils.isEmpty(criteria.getCheckListId())){
            whereCause.add("t.checkListId = :checkListId");
            params.put("checkListId", criteria.getCheckListId());
        }
        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
