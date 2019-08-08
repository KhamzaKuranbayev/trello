package uz.genesis.trello.repository.organization;

import uz.genesis.trello.criterias.organization.OrganizationCriteria;
import uz.genesis.trello.domain.organization.Organization;
import uz.genesis.trello.repository.IGenericCrudRepository;

public interface IOrganizationRepository extends IGenericCrudRepository<Organization, OrganizationCriteria> {

}
