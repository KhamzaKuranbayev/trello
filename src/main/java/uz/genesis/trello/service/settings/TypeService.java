package uz.genesis.trello.service.settings;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import uz.genesis.trello.criterias.settings.TypeCriteria;
import uz.genesis.trello.dto.settings.TypeCreateDto;
import uz.genesis.trello.dto.settings.TypeDto;
import uz.genesis.trello.dto.settings.TypeUpdateDto;
import uz.genesis.trello.mapper.GenericMapper;
import uz.genesis.trello.repository.settings.ITypeRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;

/**
 * Created by 'javokhir' on 01/07/2019
 */

public class TypeService extends AbstractCrudService<TypeDto, TypeCreateDto, TypeUpdateDto, TypeCriteria, ITypeRepository> {

    /**
     * Common logger for use in subclasses.
     */
    protected final Log logger = LogFactory.getLog(getClass());

    private GenericMapper genericMapper;

    public TypeService(ITypeRepository repository, BaseUtils utils) {
        super(repository, utils);
    }
}
