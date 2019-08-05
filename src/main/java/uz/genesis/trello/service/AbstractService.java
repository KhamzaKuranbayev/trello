package uz.genesis.trello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uz.genesis.trello.criterias.GenericCriteria;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.repository.IAbstractRepository;
import uz.genesis.trello.repository.IGenericRepository;
import uz.genesis.trello.service.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;

import java.util.List;

/**
 * Created by 'javokhir' on 12/06/2019
 */

public abstract class AbstractService<T, C extends GenericCriteria, R extends IGenericRepository> {

    protected final R  repository;
    protected final BaseUtils utils;
    protected final IErrorRepository errorRepository;

    @Autowired
    public AbstractService(R repository, BaseUtils utils, IErrorRepository errorRepository) {
        this.repository = repository;
        this.utils = utils;
        this.errorRepository = errorRepository;
    }

    public ResponseEntity<DataDto<T>> get(Long id) {
        return null;
    }

    public ResponseEntity<DataDto<List<T>>> getAll(C criteria) {
        return null;
    }
}
