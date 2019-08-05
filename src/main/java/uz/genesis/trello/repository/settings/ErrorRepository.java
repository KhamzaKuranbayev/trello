package uz.genesis.trello.repository.settings;

import org.springframework.stereotype.Repository;
import uz.genesis.trello.criterias.GenericCriteria;
import uz.genesis.trello.dao.FunctionParam;
import uz.genesis.trello.dao.GenericDao;
import uz.genesis.trello.domain.Auditable;
import uz.genesis.trello.enums.ErrorCodes;
import uz.genesis.trello.repository.settings.IErrorRepository;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ErrorRepository extends GenericDao<Auditable, GenericCriteria> implements IErrorRepository {

    @Override
    public String getErrorMessage(String errorCode, String params) {
        return call(getParamsForFunction(errorCode, params), "getErrorMessage", Types.VARCHAR);
    }

    @Override
    public String getErrorMessage(ErrorCodes errorCode, String params) {
        return call(getParamsForFunction(errorCode.code, params), "getErrorMessage", Types.VARCHAR);
    }

    private List<FunctionParam> getParamsForFunction(String errorCode, String params) {
        List<FunctionParam> funcParams = new ArrayList<>();
        funcParams.add(new FunctionParam(errorCode, Types.VARCHAR));
        funcParams.add(new FunctionParam(params, Types.VARCHAR));
        funcParams.add(new FunctionParam(userSession.getUser().getId(), Types.BIGINT));

        return funcParams;
    }


}
