package uz.genesis.trello.repository.main;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.main.ProjectColumnCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.main.ProjectColumn;

import java.util.List;
import java.util.Map;

@Repository
public class ProjectColumnRepository extends GenericDao<ProjectColumn, ProjectColumnCriteria> implements IProjectColumnRepository {

    @Override
    protected void defineCriteriaOnQuerying(ProjectColumnCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getSelfId())){
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if(!utils.isEmpty(criteria.getCodeName())){
            whereCause.add("t.getCodeName = :codeName");
            params.put("codeName", criteria.getCodeName());
        }
        if(!utils.isEmpty(criteria.getProjectId())){
            whereCause.add("t.projectId = :projectId");
            params.put("projectId", criteria.getProjectId());
        }
        if(!utils.isEmpty(criteria.getName())){
            whereCause.add("t.name = :name");
            params.put("name", criteria.getName());
        }


        onDefineWhereCause(criteria,whereCause,params,queryBuilder);
    }
}
