package co.da.nw.dao;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

import org.joda.time.LocalDateTime;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.da.nw.dao.CustomerRepository;
import co.da.nw.dao.EmployeeRepository;
import co.da.nw.dao.OrderRepository;
import co.da.nw.dao.ShipperRepository;
import co.da.nw.domain.Customer;
import co.da.nw.domain.Employee;
import co.da.nw.domain.Order;
import co.da.nw.domain.Shipper;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional()
public class OrderRepositoryTest {

    @Autowired
    OrderRepository repository;
    
    @Autowired
    CustomerRepository cRepository;
    
    @Autowired
    EmployeeRepository eRepository;
    
    @Autowired
    ShipperRepository sRepository;
    
    @Test
    public void testFindByCustomer() {
        String custId = "THECR";
        Customer cust = cRepository.findOne(custId);
        assertThat(cust != null, is(true));
        
        List<Order> orders = repository.findByCustomer(cust);
        assertThat(orders != null, is(true));
        for(Order o : orders) {
            assertThat(custId.equals(o.getCustomer().getCustomerId()), is(true));
        }
    }

    @Test
    public void testFindByEmployee() {
        Integer empId = 1;
        Employee emp = eRepository.findOne(empId);
        assertThat(emp != null, is(true));
        
        List<Order> orders = repository.findByEmployee(emp);
        assertThat(orders != null, is(true));
        for(Order o : orders) {
            assertThat(empId.equals(o.getEmployee().getEmployeeId()), is(true));
        }
        
    }

    @Test
    public void testFindByShipper() {
        Long shipperId = 3L;
        Shipper shipper = sRepository.findOne(shipperId);
        assertThat(shipper != null, is(true));
        
        List<Order> orders = repository.findByShipper(shipper);
        assertThat(orders != null, is(true));
        for(Order o : orders) {
            assertThat(shipperId.equals(o.getShipper().getId()), is(true));
        }
        
    }

    @Test
    public void testFindAll() {
        long count = repository.count();
        List<Order> orders = repository.findAll();
        assertThat(Long.valueOf(orders.size()), is(count));
    }

    @Test
    public void testFindAllSort() {
        long count = repository.count();
        List<Order> orders = repository.findAll(new Sort(Sort.Direction.ASC, "customer"));
        assertThat(Long.valueOf(orders.size()), is(count));
        int size = orders.size();
        assertThat(Long.valueOf(count), is(Long.valueOf(size)));
        if(size > 1) {
            Comparator<Order> comparator = createComparator();
            Order prev = orders.get(0);
            for(int i = 1; i < size; i++) {
                Order current = orders.get(i);
                // prev must be greater than or equal to the current Employee.
                assertThat(comparator.compare(prev, current), is(lessThanOrEqualTo(0)));
                prev = current;
            }
        }
    }
    
    @Ignore
    private Comparator<Order> createComparator() {
        return new Comparator<Order>() {

            @Override
            public int compare(Order o1, Order o2) {
                // Just compare the two Customer objects since the test sort method sorts them by Customer.
                return ComparisonChain.start()
                        .compare(o1.getCustomer(), o2.getCustomer(), Ordering.natural().nullsFirst())
                        .result();
            }
        };
    }

    @Test
    public void testSaveIterableOfS() {
        Customer cust1 = cRepository.findOne("TOMSP");
        Employee emp1 = eRepository.findOne(1);
        Shipper shipper1 = sRepository.findOne(3L);
        Order.Builder builder = new Order.Builder();
        builder.setCustomer(cust1);
        builder.setEmployee(emp1);
        builder.setShipper(shipper1);
        builder.setOrderDate(new LocalDateTime("2013-10-20"));
        builder.setRequiredDate(new LocalDateTime("2013-10-30"));
        builder.setShippedDate(new LocalDateTime("2013-10-22"));
        builder.setFreight(BigDecimal.valueOf(115.93));
        builder.setShipName("Jordan Jenkins");
        builder.setShipAddress("123 Main");
        builder.setShipCity("Los Angeles");
        builder.setShipRegion("CA");
        builder.setShipCountry("US");
        builder.setShipPostalCode("90210");
        Order order1 = builder.build();
        
        Customer cust2 = cRepository.findOne("SUPRD");
        Employee emp2 = eRepository.findOne(9);
        Shipper shipper2 = sRepository.findOne(4L);
        builder.setCustomer(cust2);
        builder.setEmployee(emp2);
        builder.setShipper(shipper2);
        builder.setOrderDate(new LocalDateTime("2013-10-20"));
        builder.setRequiredDate(new LocalDateTime("2013-10-30"));
        builder.setShippedDate(new LocalDateTime("2013-10-22"));
        builder.setFreight(BigDecimal.valueOf(115.93));
        builder.setShipName("Melvin Murphy");
        builder.setShipAddress("123 Main");
        builder.setShipCity("Memphis");
        builder.setShipRegion("TN");
        builder.setShipCountry("US");
        builder.setShipPostalCode("38111");
        Order order2 = builder.build();
        
        ImmutableList<Order> orders = ImmutableList.of(order1, order2);
        List<Order> saved = repository.save(orders);
        assertThat(orders.equals(saved), is(true));
        repository.flush();
    }

    @Test
    public void testSaveAndFlush() {
        Customer cust = cRepository.findOne("VINET");
        Employee emp = eRepository.findOne(8);
        Shipper shipper = sRepository.findOne(2L);
        Order.Builder builder = new Order.Builder();
        builder.setCustomer(cust);
        builder.setEmployee(emp);
        builder.setShipper(shipper);
        builder.setOrderDate(new LocalDateTime("2013-10-20"));
        builder.setRequiredDate(new LocalDateTime("2013-10-30"));
        builder.setShippedDate(new LocalDateTime("2013-10-22"));
        builder.setFreight(BigDecimal.valueOf(115.93));
        builder.setShipName("Jordan Jenkins");
        builder.setShipAddress("123 Main");
        builder.setShipCity("Los Angeles");
        builder.setShipRegion("CA");
        builder.setShipCountry("US");
        builder.setShipPostalCode("90210");
        Order order = builder.build();
        
        Order saved = repository.save(order);
        assertThat(saved, is(order));
    }

}
