package pl.rest.project.RESTProject.service;

import java.util.List;

import pl.rest.project.RESTProject.entity.Employee;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    void save(Employee employee);
    void deleteById(int id);
}
