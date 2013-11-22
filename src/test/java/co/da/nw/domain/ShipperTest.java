package co.da.nw.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import co.da.nw.domain.Shipper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
public class ShipperTest {

	@Test
	public void testHashCode() {
		Shipper s1 = new Shipper("ACME", "999-999-9999");
		Shipper s2 = new Shipper("ACME", "999-999-9999");
		Shipper s3 = new Shipper("ACME", "999-999-9991");
		assertThat(s1.hashCode(), is(s2.hashCode()));
		assertTrue(s1.hashCode() != s3.hashCode());
	}
	
	@Test
	public void testHashCodeAndEquals() {
		// The equals method compares Strings ignoring case, so their hash codes should be the same, too.
		Shipper s1 = new Shipper("ACME", "999-999-9999");
		Shipper s2 = new Shipper("acme", "999-999-9999");
		
		assertThat(s1.equals(s2), is(true));
		assertThat(s1.hashCode() == s2.hashCode(), is(true));
	}

	@Test
	public void testToString() {
		Shipper s1 = new Shipper("ACME", "999-999-9999");
		try { System.out.println("s1: " + s1); } catch(Exception e) { e.printStackTrace(); fail("Shipper.toString threw exception"); }
	}

	@Test
	public void testEqualsObject() {
		Shipper s1 = new Shipper("ACME", "999-999-9999");
		Shipper s2 = new Shipper("ACME", "999-999-9999");
		Shipper s3 = new Shipper("ACME", "999-999-9991");
		Shipper s4 = new Shipper("ACME", null);
		assertThat(s1.equals(s2), is(true));
		assertThat(s1.equals(s3), is(false));
		assertThat(s1.equals(s4), is(false));
	}

	@Test
	public void testCompareTo() {
		Shipper s1 = new Shipper("ACME", "999-999-9999");
		Shipper s2 = new Shipper("ACME", "999-999-9999");
		Shipper s3 = new Shipper("ACME", "999-999-9991");
		Shipper s4 = new Shipper("ACME", null);
		assertThat(s1.compareTo(s2), is(0));
		assertTrue(s1.compareTo(s3) > 0);
		assertTrue(s1.compareTo(s4) > 0);
		assertTrue(s1.compareTo(null) > 0);
	}

}
