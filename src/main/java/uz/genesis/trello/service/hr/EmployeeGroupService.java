package uz.genesis.trello.service.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.genesis.trello.criterias.hr.EmployeeCriteria;
import uz.genesis.trello.dto.CrudDto;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.mapper.hr.EmployeeMapper;
import uz.genesis.trello.repository.hr.IEmployeeGroupRepository;
import uz.genesis.trello.repository.hr.IEmployeeRepository;
import uz.genesis.trello.service.AbstractCrudService;
import uz.genesis.trello.utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeGroupService  extends AbstractCrudService<GenericDto, CrudDto, CrudDto, EmployeeCriteria, IEmployeeGroupRepository>  implements IEmployeeGroupService{
    private final IEmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

    @Autowired
    public EmployeeGroupService(IEmployeeGroupRepository repository, BaseUtils utils, IEmployeeRepository employeeRepository, EmployeeMapper mapper) {
        super(repository, utils);
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<DataDto<List<EmployeeDto>>> getEmployee(EmployeeCriteria criteria) {
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        repository.findAll(criteria).forEach(employeeGroup -> employeeDtoList.add(mapper.toDto(employeeRepository.find(employeeGroup.getEmployeeId()))));
        return new ResponseEntity<>(new DataDto<>(employeeDtoList), HttpStatus.OK);
    }
}