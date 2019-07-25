package uz.genesis.trello.repository.file;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.file.ResourceFileCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.files.ResourceFile;

import java.util.List;
import java.util.Map;

@Repository
public class ResourceFileRepository extends GenericDao<ResourceFile, ResourceFileCriteria> implements IResourceFileRepository {
    @Override
    protected void defineCriteriaOnQuerying(ResourceFileCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if (!utils.isEmpty(criteria.getSelfId())) {
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if (!utils.isEmpty(criteria.getMimeType())) {
            whereCause.add("t.mimeType = :mimeType");
            params.put("mimeType", criteria.getMimeType());
        }
        if (!utils.isEmpty(criteria.getSize())) {
            whereCause.add("t.size = :size");
            params.put("size", criteria.getSize());
        }
        if (!utils.isEmpty(criteria.getName())) {
            whereCause.add("t.name = :name");
            params.put("name", criteria.getName());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
