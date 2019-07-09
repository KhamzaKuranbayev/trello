package uz.genesis.trello.service;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.GenericCriteria;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.repository.IAbstractRepository;
import uz.genesis.trello.utils.BaseUtils;

import javax.validation.constraints.NotNull;

/**
 * Created by 'javokhir' on 26/06/2019
 */

public abstract class AbstractCrudService<T, CR extends CrudDto, UP extends CrudDto, C extends GenericCriteria, R extends IAbstractRepository> extends AbstractService<T, C, R> {

    public AbstractCrudService(R repository, BaseUtils utils) {
        super(repository, utils);
    }

    public ResponseEntity<DataDto<GenericDto>> create(@NotNull CR dto) {
        return null;
    }

    public ResponseEntity<DataDto<T>> update(@NotNull UP dto) {
        return null;
    }

    public ResponseEntity<DataDto<Boolean>> delete(@NotNull Long id) {
        return null;
    }
}
