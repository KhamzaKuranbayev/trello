package uz.genesis.trello.repository.achievement;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.achievement.CoinSettingsCriteria;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.achievement.CoinSettings;

import java.util.List;
import java.util.Map;

@Repository
public class CoinSettingsRepository extends GenericDao<CoinSettings, CoinSettingsCriteria> implements ICoinSettingsRepository {

    @Override
    protected void defineCriteriaOnQuerying(CoinSettingsCriteria criteria, List<String> whereCause, Map<String, Object> params, StringBuilder queryBuilder) {
        if(!utils.isEmpty(criteria.getSelfId())){
            whereCause.add("t.id = :selfId");
            params.put("selfId", criteria.getSelfId());
        }
        if(!utils.isEmpty(criteria.getCoins())){
            whereCause.add("t.coins = :coins");
            params.put("coins", criteria.getCoins());
        }

        onDefineWhereCause(criteria, whereCause, params, queryBuilder);
    }
}
