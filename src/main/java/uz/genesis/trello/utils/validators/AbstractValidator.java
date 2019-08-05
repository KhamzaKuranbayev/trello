package uz.genesis.trello.utils.validators;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import uz.genesis.trello.repository.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;

/**
 * Created by 'javokhir' on 27/06/2019
 */

public abstract class AbstractValidator<T> {

    protected final Log logger;
    protected IErrorRepository repository;
    protected BaseUtils utils;
//    private IErrorsRepository repository;

    @SuppressWarnings("unchecked")
    @Autowired
    public AbstractValidator(BaseUtils utils, IErrorRepository repository) {
        this.utils = utils;
        this.repository = repository;
        this.logger = LogFactory.getLog(getClass());
    }
}
