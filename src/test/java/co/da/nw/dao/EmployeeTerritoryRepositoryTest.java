package co.da.nw.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.da.nw.dao.EmployeeRepository;
import co.da.nw.dao.EmployeeTerritoryPredicates;
import co.da.nw.dao.EmployeeTerritoryRepository;
import co.da.nw.dao.EmployeeTerritorySpecifications;
import co.da.nw.dao.TerritoryRepository;
import co.da.nw.domain.Employee;
import co.da.nw.domain.EmployeeTerritory;
import co.da.nw.domain.EmployeeTerritoryPk;
import co.da.nw.domain.EmployeeTerritoryPk_;
import co.da.nw.domain.EmployeeTerritory_;
import co.da.nw.domain.Territory;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional()
public class EmployeeTerritoryRepositoryTest {
    
    @Autowired
    EmployeeTerritoryRepository repository;
    
    @Autowired
    EmployeeRepository eRepository;
    
    @Autowired
    TerritoryRepository tRepository;
    
//    @Autowired
//    LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @Test
    public void testFindAll() {
        long count = repository.count();
        
        List<EmployeeTerritory> etList = repository.findAll();
        
        assertThat(Long.valueOf(etList.size()), is(count));
    }

    @Test
    public void testFindAllSort() {
        long count = repository.count();
        
        List<EmployeeTerritory> etList = repository.findAll(new Sort(Sort.Direction.ASC, "pk"));
        
        int size = etList.size();
        assertThat(Long.valueOf(size), is(count));
        
        for(EmployeeTerritory et: etList) System.out.println("et: " + et);
        
        if(size > 1) {
            // Must create a custom comparator because Employee's implementation of Comparable does not evaluate the
            // auto-incremented employeeId field.
            Comparator<EmployeeTerritory> comparator = createComparator();
            EmployeeTerritory prev = etList.get(0);
            for(int i = 1; i < size; i++) {
                EmployeeTerritory current = etList.get(i);
                // prev must be greater than or equal to the current EmployeeTerritory.
                assertThat(comparator.compare(prev, current), is(lessThanOrEqualTo(0)));
                prev = current;
            }
        }
        
    }
    
    @Ignore
    private Comparator<EmployeeTerritory> createComparator() {
        return new Comparator<EmployeeTerritory>() {

            @Override
            public int compare(EmployeeTerritory o1, EmployeeTerritory o2) {
                // Compare the ids only of Employee and Territory in EmployeeTerritoryPk
                EmployeeTerritoryPk pk1 = o1.getPk();
                EmployeeTerritoryPk pk2 = o2.getPk();
                
                return ComparisonChain.start()
                        .compare(pk1.getEmployee().getEmployeeId(), pk2.getEmployee().getEmployeeId())
                        .compare(pk1.getTerritory().getTerritoryId(), pk2.getTerritory().getTerritoryId())
                        .result();
            }
        };
    }

    @Test
    public void testSaveIterableOfS() {
        Employee e = eRepository.findOne(9);
        Territory t = tRepository.findOne("29202");
        EmployeeTerritory et1 = new EmployeeTerritory(new EmployeeTerritoryPk(e, t));
        
        e = eRepository.findOne(3);
        t = tRepository.findOne("60179");
        EmployeeTerritory et2 = new EmployeeTerritory(new EmployeeTerritoryPk(e, t));
        
        ImmutableList<EmployeeTerritory> etList = ImmutableList.of(et1, et2);
        
        List<EmployeeTerritory> saved = repository.save(etList);
        
        assertThat(etList, is(saved));
    }

    @Test
    public void testSaveAndFlush() {
        Employee e = eRepository.findOne(9);
        Territory t = tRepository.findOne("29202");
        
        EmployeeTerritory et = new EmployeeTerritory(new EmployeeTerritoryPk(e, t));
        
        EmployeeTerritory saved = repository.saveAndFlush(et);
        
        assertThat(et, is(saved));
    }

    @Test
    public void testFindAllSpecificationOfEmployee() {
        Employee employee = eRepository.findOne(2);
        
        assertThat(employee != null, is(true));
        
        List<EmployeeTerritory> etList = repository.findAll(EmployeeTerritorySpecifications.employeeIs(employee));
        
        assertThat(etList != null, is(true));
        
        assertThat(etList.size() > 0, is(true));
        
        for(EmployeeTerritory et : etList) {
            assertThat(et.getPk().getEmployee().getEmployeeId(), is(2));
        }
        
//        EntityManager entityManager = entityManagerFactory.getObject().createEntityManager();
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<EmployeeTerritory> query =
//                cb.createQuery(EmployeeTerritory.class);
//        Root<EmployeeTerritory> root = query.from(EmployeeTerritory.class);
//        Path<EmployeeTerritoryPk> pkPath = root.get(EmployeeTerritory_.pk);
//        Path<Employee> empPath = pkPath.get(EmployeeTerritoryPk_.employee);
//        query.where(cb.equal(empPath, employee));
//        
//        List<EmployeeTerritory> result = entityManager.createQuery(query).getResultList();
    }

    @Test
    public void testFindAllSpecificationOfTerritory() {
        String id = "07960";
        Territory territory = tRepository.findOne(id);
        
        assertThat(territory != null, is(true));
        
        List<EmployeeTerritory> etList = repository.findAll(EmployeeTerritorySpecifications.territoryIs(territory));
        
        assertThat(etList != null, is(true));
        
        assertThat(etList.size() > 0, is(true));
        
        for(EmployeeTerritory et : etList) {
            assertThat(et.getPk().getTerritory().getTerritoryId(), is(id));
        }
    }

    @Test
    public void testFindAllSpecificationOfEmployeeSort() {
        Employee employee = eRepository.findOne(2);
        
        assertThat(employee != null, is(true));

        // This sort will work, but only because all the employees in the list will be the same employee. Because
        // we can only sort by EmployeeTerritoryPk, it evaluates the employee's id value, which the Employee
        // object's compare method does not evaluate because it is an auto-increment value that is not assigned
        // when an Employee object is created, so it can change.
        List<EmployeeTerritory> etList = repository.findAll(EmployeeTerritorySpecifications.employeeIs(employee),
                new Sort(Sort.Direction.ASC, "pk"));
        
        assertThat(etList != null, is(true));
        
        int size = etList.size();
        
        assertThat(size > 0, is(true));
        
        if(size > 1) {
            EmployeeTerritory prev = etList.get(0);
            assertThat(prev.getPk().getEmployee().getEmployeeId(), is(2));
            for(int i = 1; i < size; i++) {
                EmployeeTerritory current = etList.get(i);
                // prev must be greater than or equal to the current EmployeeTerritory.
                assertThat(prev.compareTo(current), is(lessThanOrEqualTo(0)));
                assertThat(etList.get(i).getPk().getEmployee().getEmployeeId(), is(2));
                prev = current;
            }
        }
    }
    
    // Test of QueryDsl
    
    @Test
    public void testFindAllPredicateOfTerritory() {
        String id = "07960";
        Territory territory = tRepository.findOne(id);
        
        assertThat(territory != null, is(true));
        
        Iterable<EmployeeTerritory> etList = repository.findAll(EmployeeTerritoryPredicates.territoryIs(territory));
        
        assertThat(etList != null, is(true));
        
        assertThat(etList.iterator().hasNext(), is(true));
        
        for(EmployeeTerritory et : etList) {
            assertThat(et.getPk().getTerritory().getTerritoryId(), is(id));
        }
    }
    
    @Test
    public void testFindAllPredicateOfEmployee() {
        Employee employee = eRepository.findOne(2);
        
        assertThat(employee != null, is(true));
        
        Iterable<EmployeeTerritory> etList = repository.findAll(EmployeeTerritoryPredicates.employeeIs(employee));
        
        assertThat(etList != null, is(true));
        
        assertThat(etList.iterator().hasNext(), is(true));
        
        for(EmployeeTerritory et : etList) {
            assertThat(et.getPk().getEmployee().getEmployeeId(), is(2));
        }
    }

}
