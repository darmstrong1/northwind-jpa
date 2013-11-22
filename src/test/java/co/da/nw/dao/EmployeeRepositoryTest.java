package co.da.nw.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.da.nw.dao.EmployeeRepository;
import co.da.nw.domain.Employee;

import com.google.common.collect.ImmutableList;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional()
public class EmployeeRepositoryTest {
    
    @Autowired
    EmployeeRepository repository;

    @Test
    public void testFindByLastName() {
        String lastName = "Davolio";
        List<Employee> eList = repository.findByLastName(lastName);
        
        assertThat(eList.size(), is(1));
        assertThat(eList.get(0).getLastName(), is(lastName));
    }
    
    @Test
    public void testFindByLastNameAndFirstName() {
        String lastName = "Peacock";
        String firstName = "Margaret";
        List<Employee> eList = repository.findByLastNameAndFirstName(lastName, firstName);
        
        assertThat(eList.size(), is(1));
        assertThat(eList.get(0).getLastName(), is(lastName));
        assertThat(eList.get(0).getFirstName(), is(firstName));
    }

    @Test
    public void testFindAll() {
        long count = repository.count();
        List<Employee> employees = repository.findAll();
        assertThat(Long.valueOf(employees.size()), is(count));
    }

    @Test
    public void testFindAllSort() {
        long count = repository.count();
        List<Employee> employees = repository.findAll(new Sort(Sort.Direction.ASC, "lastName", "firstName"));
        assertThat(Long.valueOf(employees.size()), is(count));
        int size = employees.size();
        assertThat(Long.valueOf(count), is(Long.valueOf(size)));
        if(size > 1) {
            Employee prev = employees.get(0);
            for(int i = 1; i < size; i++) {
                Employee current = employees.get(i);
                // prev must be greater than or equal to the current Employee.
                assertThat(prev.compareTo(current), is(lessThanOrEqualTo(0)));
                prev = current;
            }
        }
    }

    @Test
    public void testSaveIterableOfS() {
        Employee.Builder builder = new Employee.Builder();
        builder.setTitle("Sales Manager")
        .setTitleCourtesy("Mr.")
        .setBirthDate(new LocalDateTime("1972-01-15"))
        .setHireDate(new LocalDateTime("2004-06-15"))
        .setAddress("35 San Gabriel Ct")
        .setCity("Old Hickory")
        .setRegion("TN")
        .setPostalCode("37138")
        .setCountry("USA")
        .setHomePhone("615-222-2222");
        
        Employee e1 = builder.build("Howard", "Joe");
        
        builder.setTitle("Sales Representative")
        .setTitleCourtesy("Miss")
        .setBirthDate(new LocalDateTime("1972-01-15"))
        .setHireDate(new LocalDateTime("2004-06-15"))
        .setAddress("22 Glengarry")
        .setCity("Nashville")
        .setRegion("TN")
        .setPostalCode("37217")
        .setCountry("USA")
        .setHomePhone("615-333-3333");
        
        Employee e2 = builder.build("Turner", "Roxeanne");
        
        ImmutableList<Employee> employees = ImmutableList.of(e1, e2);
        List<Employee> saved = repository.save(employees);
        assertThat(employees.equals(saved), is(true));
    }

    @Test
    public void testSaveAndFlush() {
        Employee.Builder builder = new Employee.Builder();
        builder.setTitle("Sales Manager")
        .setTitleCourtesy("Mr.")
        .setBirthDate(new LocalDateTime("1972-01-15"))
        .setHireDate(new LocalDateTime("2004-06-15"))
        .setAddress("35 San Gabriel Ct")
        .setCity("Old Hickory")
        .setRegion("TN")
        .setPostalCode("37138")
        .setCountry("USA")
        .setHomePhone("615-222-2222");
        
        Employee e = builder.build("Howard", "Joe");
        repository.saveAndFlush(e);
        Integer id = e.getEmployeeId();
        Employee saved = repository.findOne(id);
        assertThat(saved, is(e));
        
    }

}
