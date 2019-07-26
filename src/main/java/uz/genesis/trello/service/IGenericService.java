package uz.genesis.trello.service;

import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.GenericCriteria;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.auth.IAuthService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 'javokhir' on 12/06/2019
 */


public interface IGenericService<T, ID extends Serializable, C extends GenericCriteria> extends IAuthService {

    ResponseEntity<DataDto<T>> get(ID id);

    ResponseEntity<DataDto<List<T>>> getAll(C criteria);
}
