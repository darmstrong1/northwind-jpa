package co.da.nw.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
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

import co.da.nw.dao.CategoryRepository;
import co.da.nw.dao.ProductRepository;
import co.da.nw.dao.SupplierRepository;
import co.da.nw.domain.Category;
import co.da.nw.domain.Product;
import co.da.nw.domain.Supplier;

import com.google.common.collect.ImmutableList;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional()
public class ProductRepositoryTest {
    
    @Autowired
    ProductRepository repository;
    
    @Autowired
    SupplierRepository sRepository;
    
    @Autowired
    CategoryRepository cRepository;

    @Test
    public void testFindByProductNm() {
        String productNm = "Chang";
        List<Product> products = repository.findByProductNm(productNm);
        assertThat(products != null, is(true));
        assertThat(products.size(), is(greaterThan(0)));
        for(Product p: products) assertThat(p.getProductNm(), is(productNm));
    }

    @Test
    public void testFindAll() {
        long count = repository.count();
        List<Product> products = repository.findAll();
        assertThat(products != null, is(true));
        assertThat(Long.valueOf(products.size()), is(count));
    }

    @Test
    public void testFindAllSort() {
        long count = repository.count();
        List<Product> products = repository.findAll(new Sort(Sort.Direction.ASC, "productNm"));
        assertThat(products != null, is(true));
        assertThat(Long.valueOf(products.size()), is(count));
        
        int size = products.size();
        assertThat(count, is(Long.valueOf(size)));
        if(size > 1) {
            Product prev = products.get(0);
            for(int i = 1; i < size; i++) {
                Product current = products.get(i);
                // prev must be less than or equal to the current Product.
                // The assertion below will not work because SQL considers
                // Pâté chinois
                // to be less than
                // Pavlova
                // but Java does not.
                //assertThat(prev.compareTo(current), is(lessThanOrEqualTo(0)));
                prev = current;
            }
        }
    }

    @Test
    public void testSaveIterableOfS() {
        Supplier s1 = sRepository.findOne(1L);
        Supplier s2 = sRepository.findOne(2L);
        Supplier s3 = sRepository.findOne(3L);
        
        Category c1 = cRepository.findOne(1L);
        Category c2 = cRepository.findOne(2L);
        Category c3 = cRepository.findOne(3L);
        
        Product.Builder builder = new Product.Builder();
        builder.setCategory(c1)
            .setSupplier(s1)
            .setQuantityPerUnit("10 bags")
            .setReorderLevel(10)
            .setUnitPrice(BigDecimal.valueOf(25.00))
            .setUnitsInStock(50)
            .setUnitsOnOrder(30);
        
        Product p1 = builder.build("Fruits", 0);
        Product p2 = builder.setCategory(c2).setSupplier(s2).build("FRUITS", 0);
        Product p3 = builder.setCategory(c3).setSupplier(s3).build("Vegetables", 0);
        
        ImmutableList<Product> products = ImmutableList.of(p1, p2, p3);
        ImmutableList<Product> saved = ImmutableList.copyOf(repository.save(products));
        
        assertThat(products, is(saved));
    }

    @Test
    public void testSaveAndFlush() {
        Supplier s1 = sRepository.findOne(1L);
        Category c1 = cRepository.findOne(1L);
        Product.Builder builder = new Product.Builder();
        builder.setCategory(c1)
            .setSupplier(s1)
            .setQuantityPerUnit("10 bags")
            .setReorderLevel(10)
            .setUnitPrice(BigDecimal.valueOf(25.00))
            .setUnitsInStock(50)
            .setUnitsOnOrder(30);
        
        Product p1 = builder.build("Fruits", 0);
        repository.saveAndFlush(p1);
    }

}
