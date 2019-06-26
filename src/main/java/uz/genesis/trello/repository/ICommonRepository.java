package uz.genesis.trello.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import uz.genesis.trello.domain.Auditable;

/**
 * Created by 'javokhir' on 10/06/2019
 */
//https://habr.com/ru/post/423741/
@NoRepositoryBean
public interface ICommonRepository<T extends Auditable> extends CrudRepository<T, Long>, IAbstractRepository {


}
