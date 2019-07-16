package uz.genesis.trello.repository.main;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.main.ProjectTagCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.main.ProjectTag;

import java.util.List;
import java.util.Map;

@Repository
public class ProjectTagRepository extends GenericDao<ProjectTag, ProjectTagCriteria> implements IProjectTagRepository  {

    @Override
    protected void defineCriteriaOnQuerying(ProjectTagCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getSelfId())){
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if(!utils.isEmpty(criteria.getColor())){
            whereCause.add("t.color = :color");
            params.put("color", criteria.getColor());
        }
        if(!utils.isEmpty(criteria.getName())){
            whereCause.add("t.name= = :name");
            params.put("name", criteria.getName());
        }
        if (!utils.isEmpty(criteria.getProjectId())) {
            whereCause.add("t.projectId = :projectId");
            params.put("projectId",criteria.getProjectId());
        }


        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
