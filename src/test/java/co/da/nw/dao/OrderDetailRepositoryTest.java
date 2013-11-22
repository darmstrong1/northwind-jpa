package co.da.nw.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

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

import co.da.nw.dao.OrderDetailRepository;
import co.da.nw.dao.OrderDetailSpecifications;
import co.da.nw.dao.OrderRepository;
import co.da.nw.dao.ProductRepository;
import co.da.nw.domain.Order;
import co.da.nw.domain.OrderDetail;
import co.da.nw.domain.OrderDetailPk;
import co.da.nw.domain.Product;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional()
public class OrderDetailRepositoryTest {
    
    @Autowired
    OrderDetailRepository repository;
    
    @Autowired
    OrderRepository oRepository;
    
    @Autowired
    ProductRepository pRepository;

    @Test
    public void testFindAll() {
        long count = repository.count();
        
        List<OrderDetail> orderDetails = repository.findAll();

        assertThat(orderDetails != null, is(true));
        assertThat(Long.valueOf(orderDetails.size()), is(count));
    }

    @Test
    public void testFindAllSort() {
        long count = repository.count();
        
        List<OrderDetail> orderDetails = repository.findAll(new Sort(Sort.Direction.ASC, "pk"));

        int size = orderDetails.size();
        
        assertThat(orderDetails != null, is(true));
        assertThat(Long.valueOf(size), is(count));
        
        if(size > 1) {
            Comparator<OrderDetail> customComparator = createComparator();
            OrderDetail prev = orderDetails.get(0);
            for(int i = 1; i < size; i++) {
                OrderDetail current = orderDetails.get(i);
                // prev must be greater than or equal to the current OrderDetail.
                assertThat(customComparator.compare(prev, current), is(lessThanOrEqualTo(0)));
                prev = current;
            }
        }
    }
    
    @Ignore
    private Comparator<OrderDetail> createComparator() {
        return new Comparator<OrderDetail>() {

            @Override
            public int compare(OrderDetail o1, OrderDetail o2) {
                OrderDetailPk pk1 = o1.getPk();
                OrderDetailPk pk2 = o2.getPk();
                
                return ComparisonChain.start()
                        .compare(pk1.getOrder().getOrderId(), pk2.getOrder().getOrderId())
                        .compare(pk1.getProduct().getProductId(), pk2.getProduct().getProductId())
                        .result();
            }
        };
    }

    @Test
    public void testSaveIterableOfS() {
        Order order = oRepository.findOne(10250L);
        assertThat(order != null, is(true));
        
        Product product = pRepository.findOne(1L);
        assertThat(product != null, is(true));
        
        OrderDetailPk pk = new OrderDetailPk(order, product);
        
        OrderDetail orderDetail1 = new OrderDetail(pk, BigDecimal.valueOf(25), 3, BigDecimal.valueOf(0));
        
        product = pRepository.findOne(2L);
        assertThat(product != null, is(true));

        pk = new OrderDetailPk(order, product);
        
        OrderDetail orderDetail2 = new OrderDetail(pk, BigDecimal.valueOf(15), 5, BigDecimal.valueOf(0.50));
        
        ImmutableList<OrderDetail> orderDetails = ImmutableList.of(orderDetail1, orderDetail2);
        
        ImmutableList<OrderDetail> saved = ImmutableList.copyOf(repository.save(orderDetails));
        
        assertThat(orderDetails, is(saved));
    }

    @Test
    public void testSaveAndFlush() {
        Order order = oRepository.findOne(10250L);
        assertThat(order != null, is(true));
        
        Product product = pRepository.findOne(1L);
        assertThat(product != null, is(true));
        
        OrderDetailPk pk = new OrderDetailPk(order, product);
        
        OrderDetail orderDetail1 = new OrderDetail(pk, BigDecimal.valueOf(25), 3, BigDecimal.valueOf(0));
        
        OrderDetail saved = repository.saveAndFlush(orderDetail1);
        
        assertThat(orderDetail1, is(saved));
    }

    @Test
    public void testFindAllSpecificationOfOrder() {
        Long orderId = Long.valueOf(10248L);
        Order order = oRepository.findOne(orderId);
        List<OrderDetail> orderDetails = repository.findAll(OrderDetailSpecifications.orderIs(order));
        
        assertThat(orderDetails != null, is(true));
        assertThat(orderDetails.size(), is(greaterThan(0)));
        
        for(OrderDetail od: orderDetails) assertThat(od.getPk().getOrder().getOrderId(), is(orderId));
    }

    @Test
    public void testFindAllSpecificationOfProduct() {
        Long productId = Long.valueOf(2L);
        Product product = pRepository.findOne(productId);
        List<OrderDetail> orderDetails = repository.findAll(OrderDetailSpecifications.productIs(product));
        
        assertThat(orderDetails != null, is(true));
        assertThat(orderDetails.size(), is(greaterThan(0)));
        
        for(OrderDetail od: orderDetails) assertThat(od.getPk().getProduct().getProductId(), is(productId));
    }

}
