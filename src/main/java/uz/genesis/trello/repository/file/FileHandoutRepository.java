package uz.genesis.trello.repository.file;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.file.FileHandoutCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.files.FileHandout;

import java.util.List;
import java.util.Map;

@Repository
public class FileHandoutRepository extends GenericDao<FileHandout, FileHandoutCriteria> implements IFileHandoutRepository {
    @Override
    protected void defineCriteriaOnQuerying(FileHandoutCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!utils.isEmpty(criteria.getSelfId())) {
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if (!utils.isEmpty(criteria.getSourceId())) {
            whereCause.add("t.sourceId = :sourceId");
            params.put("sourceId", criteria.getSourceId());
        }
        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
