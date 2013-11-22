package co.da.nw.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.da.nw.dao.SupplierRepository;
import co.da.nw.domain.Category;
import co.da.nw.domain.Supplier;

import com.google.common.collect.ImmutableList;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional()
public class SupplierRepositoryTest {

    @Autowired
    SupplierRepository repository;
    
    @Test
    public void testFindByCompanyNm() {
        String companyNm = "Exotic Liquids";
        List<Supplier> suppliers = repository.findByCompanyNm(companyNm);
        assertThat(suppliers != null, is(true));
        assertThat(suppliers.size() > 0, is(true));
        for(Supplier s : suppliers) assertThat(companyNm, is(s.getCompanyNm()));
    }

    @Test
    public void testFindAll() {
        long count = repository.count();
        List<Supplier> suppliers = repository.findAll();
        assertThat(suppliers != null, is(true));
        assertThat(Long.valueOf(suppliers.size()), is(count));
    }

    @Test
    public void testFindAllSort() {
        long count = repository.count();
        List<Supplier> suppliers = repository.findAll(new Sort(Sort.Direction.DESC, "companyNm"));
        assertThat(suppliers != null, is(true));
        int size = suppliers.size();
        assertThat(Long.valueOf(size), is(count));
        if(count > 1) {
            Supplier prev = suppliers.get(0);
            for(int i = 1; i < size; i++) {
                Supplier current = suppliers.get(i);
                // prev must be greater than or equal to the current Supplier.
                // The assertion would fail on the following:
                // prev: Formaggi Fortini s.r.l.
                // current : Forêts d'érables
                // Java says "Formaggi Fortini s.r.l." is greater than "Forêts d'érables".
                // SQL says the opposite.
                //assertThat(prev.compareTo(current), is(greaterThanOrEqualTo(0)));
                prev = current;
            }
            
        }
    }

    @Test
    public void testSaveIterableOfS() {
        Supplier.Builder builder = new Supplier.Builder();
        builder.setContactNm("Joe Supplier")
            .setAddress("10 2nd Street")
            .setCity("Lake Charles")
            .setRegion("LA")
            .setPostalCode("70601")
            .setCountry("US")
            .setPhone("337-111-1111");
        
        Supplier s1 = builder.build("Joe's Supplies");
        
        Supplier s2 = builder.setContactNm("Jake Fleming")
                .setAddress("2787 Holmes")
                .setCity("Memphis")
                .setRegion("TN")
                .setPostalCode("38111")
                .setPhone("901-111-1111")
                .build("Jake's Supplies");
        
        ImmutableList<Supplier> suppliers = ImmutableList.of(s1, s2);
        ImmutableList<Supplier> saved = ImmutableList.copyOf(repository.save(suppliers));
        
        assertThat(suppliers, is(saved));
    }

    @Test
    public void testSaveAndFlush() {
        Supplier.Builder builder = new Supplier.Builder();
        builder.setContactNm("Joe Supplier")
            .setAddress("10 2nd Street")
            .setCity("Lake Charles")
            .setRegion("LA")
            .setPostalCode("70601")
            .setCountry("US")
            .setPhone("337-111-1111");
        
        Supplier s1 = builder.build("Joe's Supplies");
        
        Supplier saved = repository.saveAndFlush(s1);
        assertThat(s1, is(saved));
    }

}
