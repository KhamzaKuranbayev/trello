package uz.genesis.trello.service.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.hr.EmployeeCriteria;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.hr.EmployeeGroupDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.hr.EmployeeMapper;
import uz.genesis.trello.mapper.main.EmployeeGroupMapper;
import uz.genesis.trello.repository.hr.IEmployeeGroupRepository;
import uz.genesis.trello.repository.hr.IEmployeeRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.service.settings.IErrorRepository;
import uz.genesis.trello.utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = {"employeeGroups"})
public class EmployeeGroupService extends AbstractCrudService<GenericDto, CrudDto, CrudDto, EmployeeCriteria, IEmployeeGroupRepository> implements IEmployeeGroupService {
    private final IEmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeGroupMapper mapper;

    @Autowired
    public EmployeeGroupService(IEmployeeGroupRepository repository, BaseUtils utils, IErrorRepository errorRepository, IEmployeeRepository employeeRepository, EmployeeMapper employeeMapper, EmployeeGroupMapper mapper) {
        super(repository, utils, errorRepository);
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<DataDto<List<EmployeeDto>>> getEmployee(EmployeeCriteria criteria) {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        repository.findAll(criteria).forEach(employeeGroup -> employeeDtoList.add(employeeMapper.toDto(employeeRepository.find(employeeGroup.getEmployeeId()))));
        Long total = repository.getTotalCount(criteria);
        return new ResponseEntity<>(new DataDto<>(employeeDtoList, total), HttpStatus.OK);
    }

    @Override
    @Cacheable(key = "#root.methodName + #criteria.groupId")
    public List<EmployeeGroupDto> getAllEmployeeGroup(EmployeeCriteria criteria) {
        return mapper.toDto(repository.findAll(criteria));
    }
}
