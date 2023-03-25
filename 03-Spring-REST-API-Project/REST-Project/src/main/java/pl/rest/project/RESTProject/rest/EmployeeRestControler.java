package pl.rest.project.RESTProject.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.rest.project.RESTProject.entity.Employee;
import pl.rest.project.RESTProject.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestControler {
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestControler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {
        Employee employee =  employeeService.findById(employeeId);
        
       

        return employee;
    }

    @PostMapping("/employees")
    // we are mapping the incoming body POST request to employee object
    public Employee saveEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return employee;
    }

    
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee employeeIsInDatabase = employeeService.findById( (int)employee.getId());

        if ( employeeIsInDatabase == null) {
            throw new RuntimeException("You cant update employee that doesnt exist: id = " + employee.getId());
        }

        employeeService.save(employee);
        return employee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if ( employee == null) {
            throw new RuntimeException("You cant delete employee that doesnt exist: id = " + employeeId);
        }

        employeeService.deleteById(employeeId);
        return "Deleted emplooye with id: " + employeeId;
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(RuntimeException exc) {
        String error = exc.getMessage();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
