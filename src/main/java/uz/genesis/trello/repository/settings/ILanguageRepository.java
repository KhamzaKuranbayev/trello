package uz.genesis.trello.repository.settings;

import uz.genesis.trello.criterias.settings.LanguageCriteria;
import uz.genesis.trello.domain.settings.Language;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface ILanguageRepository extends IGenericCrudRepository<Language, LanguageCriteria> {
}
