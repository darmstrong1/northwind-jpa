package co.da.nw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.da.nw.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    
    public List<Employee> findByLastName(String lastName);
    public List<Employee> findByLastNameAndFirstName(String lastName, String firstName);

}
