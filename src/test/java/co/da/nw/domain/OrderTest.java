package co.da.nw.domain;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.joda.time.LocalDateTime;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { co.da.nw.config.ApplicationContext.class })
public class OrderTest {

    static Order order1;
    static Order order2;
    static Order order3;

    @BeforeClass
    public static void init() {
        // Build the Customer objects.
        Customer cust1 = new Customer.Builder("JIMBE", "ACME").setContactNm("Jim Beam").setContactTitle("Owner")
                .setAddress("123 Main").setCity("Nashville").setPostalCode("37217").setCountry("US")
                .setPhone("615-222-2222")
                .build();
        Customer cust2 = new Customer.Builder("JIMBE", "ACME").setContactNm("Jim BEAm").setContactTitle("Owner")
                .setAddress("123 Main").setCity("Nashville").setPostalCode("37217").setCountry("US")
                .setPhone("615-222-2222")
                .build(); // case insensitive should be equal.
        Customer cust3 = new Customer.Builder("DLLC", "APPS").setContactNm("David Smith").setContactTitle("Owner")
                .setAddress("123 Main").setCity("Memphis").setPostalCode("38111").setCountry("US")
                .setPhone("901-222-2222")
                .build();

        // Build the Employee objects.
        Employee.Builder eBuilder = new Employee.Builder();
        eBuilder.setTitle("Programmer")
                .setBirthDate(new LocalDateTime("1972-04-15"))
                .setHireDate(new LocalDateTime("2004-06-01"))
                .setAddress("35 San Gabriel Ct")
                .setCity("Old Hickory")
                .setRegion("TN")
                .setPostalCode("37138")
                .setCountry("USA")
                .setHomePhone("615-222-2222");

        Employee e1 = eBuilder.build("Richards", "Jeff");
        Employee e2 = eBuilder.build("Richards", "Jeff");
        Employee e3 = eBuilder.setHireDate(new LocalDateTime("2011-09-15")).build("Richards", "Jeff");

        // Now, the Shipper objects.
        Shipper s1 = new Shipper("ACME", "999-999-9999");
        Shipper s2 = new Shipper("ACME", "999-999-9999");
        Shipper s3 = new Shipper("ACME", "999-999-9991");

        // Build the Order objects.
        Order.Builder builder = new Order.Builder();
        builder.setCustomer(cust1);
        builder.setEmployee(e1);
        builder.setOrderDate(new LocalDateTime("2013-10-20"));
        builder.setRequiredDate(new LocalDateTime("2013-10-30"));
        builder.setShippedDate(new LocalDateTime("2013-10-22"));
        builder.setShipper(s1);
        builder.setFreight(BigDecimal.valueOf(115.93));
        builder.setShipName("Jordan Jenkins");
        builder.setShipAddress("123 Main");
        builder.setShipCity("Los Angeles");
        builder.setShipRegion("CA");
        builder.setShipCountry("US");
        builder.setShipPostalCode("90210");

        order1 = builder.build();

        // Case sensitive should be same.
        order2 = builder.setShipName("JORDAN JENKINS").setCustomer(cust2).setEmployee(e2).setShipper(s2).build();

        // Different
        order3 = builder.setCustomer(cust3).setEmployee(e3).setShipper(s3).build();
    }

    @Test
    public void testHashCode() {
        assertThat(order1.hashCode(), is(order2.hashCode()));
        assertThat(order1.hashCode() == order3.hashCode(), is(false));
    }

    @Test
    public void testToString() {
        try {
            System.out.println("order1: " + order1);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Order.toString threw exception");
        }
    }

    @Test
    public void testEqualsObject() {
        assertThat(order1.equals(order2), is(true));
        assertThat(order2.equals(order1), is(true));
        assertThat(order1.equals(order3), is(false));
    }

    @Test
    public void testCompareTo() {
        assertThat(order1.compareTo(order2), is(0));
        assertThat(order2.compareTo(order1), is(0));
        assertThat(order1.compareTo(order3) > 0, is(true));
    }

}
