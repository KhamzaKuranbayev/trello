package uz.genesis.trello.repository.main;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.main.ProjectMemberCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.main.ProjectMember;

import java.util.List;
import java.util.Map;

@Repository
public class ProjectMemberRepository extends GenericDao<ProjectMember, ProjectMemberCriteria> implements IProjectMemberRepository {
    @Override
    protected void defineCriteriaOnQuerying(ProjectMemberCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getSelfId())){
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
    onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
