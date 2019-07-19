package uz.genesis.trello.repository.main;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.main.ProjectCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.main.Project;
import uz.genesis.trello.dto.main.ProjectPercentageDto;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class ProjectRepository extends GenericDao<Project, ProjectCriteria> implements IProjectRepository {

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

    @Override
    protected Query defineQuerySelect(ProjectCriteria criteria, StringBuilder queryBuilder, boolean onDefineCount) {
        String queryStr;
        if (criteria.isPercentage()) {
            queryStr = " select new uz.genesis.trello.dto.main.ProjectPercentageDto(t, getcompletepercentage(t.id)) from  Project t " +
                    joinStringOnQuerying(criteria) +
                    " where t.deleted = 0 " + queryBuilder.toString() + onSortBy(criteria).toString();
            return entityManager.createQuery(queryStr);
        } else {
            queryStr = " select " + (onDefineCount ? " count(t) " : " t ") + " from  Project t " +
                    joinStringOnQuerying(criteria) +
                    " where t.deleted = 0 " + queryBuilder.toString() + onSortBy(criteria).toString();
            return onDefineCount ? entityManager.createQuery(queryStr, Long.class) : entityManager.createQuery(queryStr);
        }
    }




    @Override
    public List<ProjectPercentageDto> getAllPercentageProjects(ProjectCriteria criteria) {
        return super.findAllGeneric(criteria);
    }
}
