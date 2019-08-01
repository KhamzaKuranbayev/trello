package uz.genesis.trello.service.settings;

import uz.genesis.trello.criterias.settings.LanguageCriteria;
import uz.genesis.trello.dto.GenericCrudDto;
import uz.genesis.trello.dto.settings.LanguageDto;
import uz.genesis.trello.service.IGenericCrudService;

public interface ILanguageService extends IGenericCrudService<LanguageDto, GenericCrudDto, GenericCrudDto, Long, LanguageCriteria> {
}
