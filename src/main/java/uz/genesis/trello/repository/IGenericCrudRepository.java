package uz.genesis.trello.repository;

import uz.genesis.trello.criterias.GenericCriteria;
import uz.genesis.trello.domain.Auditable;

/**
 * Created by 'javokhir' on 12/06/2019
 */

public interface IGenericCrudRepository<T extends Auditable, C extends GenericCriteria> extends IGenericRepository<T, C> {

    <S extends T> S save(S entity);

    void delete(T entity);

    void deleteById(Long id);
}
