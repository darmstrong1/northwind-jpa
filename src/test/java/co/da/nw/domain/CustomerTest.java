package co.da.nw.domain;


import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import co.da.nw.domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
public class CustomerTest {

    @Test
    public void testHashCode() {
        Customer.Builder builder = new Customer.Builder();
        Customer cust1 = builder.setContactNm("Jim Beam").setContactTitle("Owner").setAddress("123 Main")
                .setCity("Nashville").setPostalCode("37217").setCountry("US").setPhone("615-222-2222")
                .build("JIMBE", "ACME");
        Customer cust2 = builder.setContactNm("Jim BEAm").setContactTitle("Owner").setAddress("123 Main")
                .setCity("Nashville").setPostalCode("37217").setCountry("US").setPhone("615-222-2222")
                .build("JIMBE", "ACME"); // case insensitive should be equal.
        Customer cust3 = builder.setContactNm("David Smith").setContactTitle("Owner").setAddress("123 Main")
                .setCity("Memphis").setPostalCode("38111").setCountry("US").setPhone("901-222-2222")
                .build("DLLC", "APPS");
        
        assertThat(cust1.hashCode(), is(cust2.hashCode()));
        assertThat(cust1.hashCode() == cust3.hashCode(), is(false));
        
    }

    @Test
    public void testToString() {
        Customer.Builder builder = new Customer.Builder();
        Customer cust1 = builder.setContactNm("Jim Beam").setContactTitle("Owner").setAddress("123 Main")
                .setCity("Nashville").setPostalCode("37217").setCountry("US").setPhone("615-222-2222")
                .build("JIMBE", "ACME");
        try { System.out.println("cust1: " + cust1); } catch(Exception e) { e.printStackTrace(); fail("Customer.toString threw exception"); }
    }

    @Test
    public void testEqualsObject() {
        Customer.Builder builder = new Customer.Builder();
        Customer cust1 = builder.setContactNm("Jim Beam").setContactTitle("Owner").setAddress("123 Main")
                .setCity("Nashville").setPostalCode("37217").setCountry("US").setPhone("615-222-2222")
                .build("JIMBE", "ACME");
        Customer cust2 = builder.setContactNm("Jim BEAm").setContactTitle("Owner").setAddress("123 Main")
                .setCity("Nashville").setPostalCode("37217").setCountry("US").setPhone("615-222-2222")
                .build("JIMBE", "ACME"); // case insensitive should be equal.
        Customer cust3 = builder.setContactNm("David Smith").setContactTitle("Owner").setAddress("123 Main")
                .setCity("Memphis").setPostalCode("38111").setCountry("US").setPhone("901-222-2222")
                .build("DLLC", "APPS");
        
        assertThat(cust1.equals(cust2), is(true));
        assertThat(cust1.equals(cust3), is(false));
        
    }

    @Test
    public void testCompareTo() {
        Customer.Builder builder = new Customer.Builder();
        Customer cust1 = builder.setContactNm("Jim Beam").setContactTitle("Owner").setAddress("123 Main")
                .setCity("Nashville").setPostalCode("37217").setCountry("US").setPhone("615-222-2222")
                .build("JIMBE", "ACME");
        Customer cust2 = builder.setContactNm("Jim BEAm").setContactTitle("Owner").setAddress("123 Main")
                .setCity("Nashville").setPostalCode("37217").setCountry("US").setPhone("615-222-2222")
                .build("JIMBE", "ACME"); // case insensitive should be equal.
        Customer cust3 = builder.setContactNm("David Smith").setContactTitle("Owner").setAddress("123 Main")
                .setCity("Memphis").setPostalCode("38111").setCountry("US").setPhone("901-222-2222")
                .build("DLLC", "APPS");
        
        assertThat(cust1.compareTo(cust2), is(0));
        assertThat(cust1.compareTo(cust3) > 0, is(true));
        assertThat(cust1.compareTo(null) > 0, is(true));
    }

}
