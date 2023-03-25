package pl.rest.project.RESTProject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.rest.project.RESTProject.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    
}
