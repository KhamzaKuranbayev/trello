package uz.genesis.trello.repository;

import uz.genesis.trello.criterias.GenericCriteria;
import uz.genesis.trello.domain.Auditable;

import java.util.List;

/**
 * Created by 'javokhir' on 11/06/2019
 */

public interface IGenericRepository<T extends Auditable, C extends GenericCriteria> extends IAbstractRepository {

    T find(Long id);

    T find(C criteria);

    Long getTotalCount(C criteria);

    List<T> findAll(C criteria);
}
