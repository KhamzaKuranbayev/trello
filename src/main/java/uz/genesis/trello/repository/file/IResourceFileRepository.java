package uz.genesis.trello.repository.file;

import uz.genesis.trello.criterias.file.ResourceFileCriteria;
import uz.genesis.trello.domain.files.ResourceFile;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface IResourceFileRepository extends IGenericCrudRepository<ResourceFile, ResourceFileCriteria> {
}
