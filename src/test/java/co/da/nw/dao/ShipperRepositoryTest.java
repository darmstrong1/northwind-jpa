package co.da.nw.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
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

import co.da.nw.dao.ShipperRepository;
import co.da.nw.domain.Shipper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional()
public class ShipperRepositoryTest {
	
	@Autowired
	ShipperRepository repository;

	@Test
	public void testFindByCompanyName() {
		String companyNm = "Speedy Express";
        List<Shipper> shippers = repository.findByCompanyName(companyNm);
        assertThat(shippers.size(), is(1));
        for(Shipper s: shippers) {
        	System.out.println(s);
        	assertThat(companyNm, is(s.getCompanyName()));
        }
	}

	@Test
	public void testFindAll() {
		long count = repository.count();
		List<Shipper> shippers = repository.findAll();
		assertThat(Long.valueOf(count), is(Long.valueOf(shippers.size())));
	}

	@Test
	public void testFindAllSort() {
		System.out.println("\"United\".compareTo(\"UPS\")" + "United".compareTo("UPS"));
		System.out.println("\"United\".compareToIgnoreCase(\"UPS\")" + "United".compareToIgnoreCase("UPS"));
		long count = repository.count();
		Sort.Order order = new Sort.Order("companyName");
		order = order.ignoreCase();
		System.out.println("order.isIgnoreCase: " + order.isIgnoreCase());
		Sort sort = new Sort(order);
		List<Shipper> shippers = repository.findAll(sort);
		int size = shippers.size();
		assertThat(Long.valueOf(count), is(Long.valueOf(size)));
		if(size > 1) {
			Shipper prev = shippers.get(0);
			for(int i = 1; i < size; i++) {
				Shipper current = shippers.get(i);
				// prev must be less than or equal to the current Shipper.
				//assertTrue(prev.compareTo(current) <= 0);
				assertThat(prev.compareTo(current), is(lessThanOrEqualTo(0)));
				prev = current;
			}
		}
		
		List<String> orderTst = new ArrayList<>();
		orderTst.add("UPS");
		orderTst.add("UNITED PACKAGE");
		Collections.sort(orderTst);
	}

	@Test
	public void testSaveIterableOfS() {
		Shipper shp1 = new Shipper("FedEx", "1-800-463-3339");
		Shipper shp2 = new Shipper("USPS", "1-800-888-8888");
		List<Shipper> shippers = new ArrayList<>();
		shippers.add(shp1);
		shippers.add(shp2);
		List<Shipper> savedShippers = repository.save(shippers);
		assertThat(shippers, is(savedShippers));
	}

	@Test
	public void testFlush() {
		Shipper shp1 = new Shipper("FedEx", "1-800-463-3339");
		Shipper savedShp = repository.save(shp1);
		Long id = savedShp.getId();
		repository.flush();
		Shipper saveConfirmed = repository.findOne(id);
		assertTrue(saveConfirmed != null);
	}

	@Test
	public void testSaveAndFlush() {
		Shipper shp1 = new Shipper("FedEx", "1-800-463-3339");
		Shipper savedShp = repository.saveAndFlush(shp1);
		Long id = savedShp.getId();
		Shipper saveConfirmed = repository.findOne(id);
		assertTrue(saveConfirmed != null);
	}

//	@Test
//	public void testDeleteAllInBatch() {
//		long count = repository.count();
//		Shipper shp1 = new Shipper("FedEx", "1-800-463-3339");
//		Shipper shp2 = new Shipper("USPS", "1-800-888-8888");
//		shp1 = repository.save(shp1);
//		shp2 = repository.save(shp2);
//		repository.flush();
//		
//		assertThat(repository.exists(shp1.getId()), is(true));
//		assertThat(repository.exists(shp2.getId()), is(true));
//		
//		repository.deleteAllInBatch();
//		
//		assertThat(repository.count(), is(count));
//	}

	@Test
	public void testFindOne() {
		Shipper shipper = repository.findOne(Long.valueOf(1));
		assertThat(shipper.getId(), is(1L));
	}

	@Test
	public void testExists() {
		assertThat(repository.exists(1L), is(true));
	}

	@Test
	public void testCount() {
		long count = repository.count();
		Shipper shp1 = new Shipper("FedEx", "1-800-463-3339");
		repository.save(shp1);
		assertThat(repository.count(), is(count+1));
	}

	@Test
	public void testDeleteID() {
		Shipper shp1 = new Shipper("FedEx", "1-800-463-3339");
		shp1 = repository.save(shp1);
		repository.delete(shp1.getId());
		assertTrue(repository.findOne(shp1.getId()) == null);
	}

	@Test
	public void testDeleteT() {
		Shipper shp1 = new Shipper("FedEx", "1-800-463-3339");
		shp1 = repository.save(shp1);
		repository.delete(shp1);
		assertTrue(repository.findOne(shp1.getId()) == null);
	}

}
