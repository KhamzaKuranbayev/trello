package uz.genesis.trello.controller.hr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.criterias.hr.EmployeeCriteria;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.EmployeeCreateDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.hr.EmployeeUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.hr.IEmployeeGroupService;
import uz.genesis.trello.service.hr.IEmployeeService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by 'javokhir' on 04/07/2019
 */

@RestController
public class EmployeeController extends ApiController<IEmployeeService> {

    private final IEmployeeGroupService employeeGroupService;

    @Autowired
    public EmployeeController(IEmployeeService service, IEmployeeGroupService employeeGroupService) {
        super(service);
        this.employeeGroupService = employeeGroupService;
    }

    @RequestMapping(value = API_PATH + V_1 + "/employees/{id}", method = RequestMethod.GET)
    public ResponseEntity<DataDto<EmployeeDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/employees", method = RequestMethod.POST)
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody EmployeeCreateDto dto) {
        return service.create(dto);
    }
    @RequestMapping(value = API_PATH+V_1+"/employees", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<DataDto<EmployeeDto>> update(@RequestBody EmployeeUpdateDto dto){
        return service.update(dto);
    }
    @RequestMapping(value = API_PATH + V_1 + "/employees/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }

    @RequestMapping(value = API_PATH + V_1 + "/employees", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<EmployeeDto>>> getAll(@Valid EmployeeCriteria criteria) {
        return service.getAll(criteria);
    }

    @RequestMapping(value = API_PATH + V_1 + "/employees/project", method = RequestMethod.GET)
    public ResponseEntity<DataDto<List<EmployeeDto>>> getByProjects(@Valid EmployeeCriteria criteria){
        return employeeGroupService.getEmployee(criteria);
    }
}
