package uz.genesis.trello.repository.settings;

import uz.genesis.trello.criterias.settings.TypeCriteria;
import uz.genesis.trello.domain.settings.Type;
import uz.genesis.trello.repository.IGenericCrudRepository;

/**
 * Created by 'javokhir' on 01/07/2019
 */

public interface ITypeRepository extends IGenericCrudRepository<Type, TypeCriteria> {
}
