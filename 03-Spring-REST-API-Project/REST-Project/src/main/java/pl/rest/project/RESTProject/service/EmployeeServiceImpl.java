package pl.rest.project.RESTProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import pl.rest.project.RESTProject.dao.EmployeeRepository;
import pl.rest.project.RESTProject.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;
    
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository EmployeeRepository) {
        this.employeeRepository = EmployeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            throw new RuntimeException("Employee with this id not found: id:" + id);
        }

        return theEmployee;
    }
    
    // We dont have to use @Transactional since JPA Spring provides this functionality
    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }


    @Override
    public void deleteById(int id) {
       employeeRepository.deleteById(id);
    }

    
}