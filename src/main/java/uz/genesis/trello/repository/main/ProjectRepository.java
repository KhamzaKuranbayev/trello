package uz.genesis.trello.repository.main;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.main.ProjectCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.main.Project;

import java.util.List;
import java.util.Map;
@Repository
public class ProjectRepository extends GenericDao<Project, ProjectCriteria> implements IProjectRepository{

    @Override
    protected void defineCriteriaOnQuerying(ProjectCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!utils.isEmpty(criteria.getSelfId())) {
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if (!utils.isEmpty(criteria.getName())) {
           whereCause.add("t.name = :name");
           params.put("name", criteria.getName());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
