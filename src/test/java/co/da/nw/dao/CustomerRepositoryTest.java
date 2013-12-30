package co.da.nw.dao;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.da.nw.domain.Customer;

import com.google.common.collect.ImmutableList;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { co.da.nw.config.ApplicationContext.class })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional()
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void testFindByCompanyNm() {
        String companyNm = "QUICK-Stop";
        Pageable pageRequest = new PageRequest(0, 10);

        Page<Customer> customers = repository.findAll(CustomerPredicates.companyNmIs(companyNm), pageRequest);
        assertThat(customers.getContent().size(), is(1));
        assertThat(customers.getContent().get(0).getCompanyNm(), is(companyNm));
    }

    @Test
    public void testFindAll() {
        long count = repository.count();
        List<Customer> customers = repository.findAll();
        assertThat(Long.valueOf(customers.size()), is(count));
    }

    @Test
    public void testFindAllSort() {
        long count = repository.count();
        List<Customer> customers = repository.findAll(new Sort(Sort.Direction.DESC, "customerId"));
        int size = customers.size();
        assertThat(Long.valueOf(count), is(Long.valueOf(size)));
        if (size > 1) {
            Customer prev = customers.get(0);
            for (int i = 1; i < size; i++) {
                Customer current = customers.get(i);
                // prev must be greater than or equal to the current Shipper.
                assertThat(prev.compareTo(current), is(greaterThanOrEqualTo(0)));
                prev = current;
            }

        }
    }

    @Test
    public void testSaveIterableOfS() {
        Customer cust1 = new Customer.Builder("JIMBE", "ACME").setContactNm("Jim Beam").setContactTitle("Owner")
                .setAddress("123 Main").setCity("Nashville").setPostalCode("37217").setCountry("US")
                .setPhone("615-222-2222")
                .build();
        Customer cust2 = new Customer.Builder("DLLCA", "APPS").setContactNm("David Smith").setContactTitle("Owner")
                .setAddress("123 Main").setCity("Memphis").setPostalCode("38111").setCountry("US")
                .setPhone("901-222-2222")
                .build();
        ImmutableList<Customer> customers = ImmutableList.of(cust1, cust2);
        List<Customer> savedCustomers = repository.save(customers);
        assertThat(customers, is(savedCustomers));
    }

    @Test
    public void testSaveAndFlush() {
        Customer cust1 = new Customer.Builder("JIMBE", "ACME").setContactNm("Jim Beam").setContactTitle("Owner")
                .setAddress("123 Main").setCity("Nashville").setPostalCode("37217").setCountry("US")
                .setPhone("615-222-2222")
                .build();
        repository.saveAndFlush(cust1);
        String id = cust1.getCustomerId();
        Customer saved = repository.findOne(id);
        assertThat(saved, is(cust1));
    }

}
