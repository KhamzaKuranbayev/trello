package uz.genesis.trello.service;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.GenericCriteria;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.response.DataDto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by 'javokhir' on 12/06/2019
 */

public interface IGenericCrudService<T, ID extends Serializable, C extends GenericCriteria> extends IGenericService<T, ID, C> {

    ResponseEntity<DataDto<GenericDto>> create(@NotNull CrudDto dto);

    ResponseEntity<DataDto<T>> update(@NotNull CrudDto dto);

    ResponseEntity<DataDto<Boolean>> delete(@NotNull ID id);

}
