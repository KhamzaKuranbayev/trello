package uz.genesis.trello.repository;

import uz.genesis.trello.criterias.GenericCriteria;
import uz.genesis.trello.dao.FunctionParam;
import uz.genesis.trello.domain.Auditable;

import java.util.List;

/**
 * Created by 'javokhir' on 12/06/2019
 */

public interface IGenericCrudRepository<T extends Auditable, C extends GenericCriteria> extends IGenericRepository<T, C> {

    /*<S extends T> S save(S entity);

    void delete(T entity);

    void deleteById(Long id);*/

    <C> Long create(C domain, String methodName);

    <C> Boolean update(C domain, String methodName);

    <R> R call(T domain, String methodName, int outParamType);

    <C, R> R call(C domain, String methodName, int outParamType);

    <R> R call(List<FunctionParam> params, String methodName, int outParamType);

    boolean delete(Long id, String methodName);
}
