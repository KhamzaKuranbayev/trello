package uz.genesis.trello.utils.validators;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import uz.genesis.trello.service.AbstractService;
import uz.genesis.trello.utils.BaseUtils;

import java.lang.reflect.ParameterizedType;

/**
 * Created by 'javokhir' on 27/06/2019
 */

public abstract class AbstractValidator<T> {

    protected final Log logger;

    protected BaseUtils utils;
//    private IErrorsRepository repository;

    @SuppressWarnings("unchecked")
    @Autowired
    public AbstractValidator(BaseUtils utils) {
        this.utils = utils;
        this.logger =  LogFactory.getLog(getClass());
    }
}
