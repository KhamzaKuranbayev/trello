package uz.genesis.trello.controller.hr;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.genesis.trello.controller.ApiController;
import uz.genesis.trello.dto.GenericDto;
import uz.genesis.trello.dto.hr.EmployeeCreateDto;
import uz.genesis.trello.dto.hr.EmployeeDto;
import uz.genesis.trello.dto.hr.EmployeeUpdateDto;
import uz.genesis.trello.dto.response.DataDto;
import uz.genesis.trello.service.hr.IEmployeeService;

/**
 * Created by 'javokhir' on 04/07/2019
 */

@RestController
public class EmployeeController extends ApiController<IEmployeeService> {

    public EmployeeController(IEmployeeService service) {
        super(service);
    }

    @GetMapping(value = API_PATH + V_1 + "/employees/{id}")
    public ResponseEntity<DataDto<EmployeeDto>> get(@PathVariable(value = "id") Long id) {
        return service.get(id);
    }

    @PostMapping(value = API_PATH + V_1 + "/employees")
    public ResponseEntity<DataDto<GenericDto>> create(@RequestBody EmployeeCreateDto dto) {
        return service.create(dto);
    }
    @RequestMapping(value = API_PATH+V_1+"/employees", method = {RequestMethod.PUT, RequestMethod.PATCH})
    public ResponseEntity<DataDto<EmployeeDto>> update(@RequestBody EmployeeUpdateDto dto){
        return service.update(dto);
    }
    @DeleteMapping(value = API_PATH + V_1 + "/employees/{id}")
    public ResponseEntity<DataDto<Boolean>> delete(@PathVariable(value = "id")Long id){
        return service.delete(id);
    }
}
