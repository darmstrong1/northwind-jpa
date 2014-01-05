package co.da.nw.domain;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
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
public class OrderDetailTest {

    static OrderDetail od1;
    static OrderDetail od2;
    static OrderDetail od3;

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
        Customer cust3 = new Customer.Builder("DLLCA", "APPS").setContactNm("David Smith").setContactTitle("Owner")
                .setAddress("123 Main").setCity("Memphis").setPostalCode("38111").setCountry("US")
                .setPhone("901-222-2222")
                .build();

        // Build the Employee objects.
        Employee.Builder eBuilder = new Employee.Builder("Richards", "Jeff");
        eBuilder.setTitle("Programmer")
                .setBirthDate(new LocalDateTime("1972-04-15"))
                .setHireDate(new LocalDateTime("2004-06-01"))
                .setAddress("35 San Gabriel Ct")
                .setCity("Old Hickory")
                .setRegion("TN")
                .setPostalCode("37138")
                .setCountry("USA")
                .setHomePhone("615-222-2222");

        Employee e1 = eBuilder.build();
        Employee e2 = eBuilder.build();
        Employee e3 = eBuilder.setHireDate(new LocalDateTime("2011-09-15")).build();

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

        Order order1 = builder.build();
        // Case sensitive should be same.
        Order order2 = builder.setShipName("JORDAN JENKINS").setCustomer(cust2).setEmployee(e2).setShipper(s2).build();
        // Different
        Order order3 = builder.setCustomer(cust3).setEmployee(e3).setShipper(s3).build();

        Supplier.Builder sBuilder = new Supplier.Builder();
        sBuilder.setContactNm("Joe Supplier")
                .setAddress("10 2nd Street")
                .setCity("Lake Charles")
                .setRegion("LA")
                .setPostalCode("70601")
                .setCountry("US")
                .setPhone("337-111-1111");

        Supplier sup1 = sBuilder.build("Joe's Supplies");
        Supplier sup2 = sBuilder.setContactNm("jOE sUPPLIER").build("Joe's Supplies");
        Supplier sup3 = sBuilder.setContactNm("Jake Fleming")
                .setAddress("2787 Holmes")
                .setCity("Memphis")
                .setRegion("TN")
                .setPostalCode("38111")
                .setPhone("901-111-1111")
                .build("Jake's Supplies");

        Category.Builder catBuilder = new Category.Builder("fruits");
        Category cat1 = catBuilder.setDescription("various fruits").build();
        // Test that it is case insensitive.
        catBuilder = new Category.Builder("FRUITS");
        Category cat2 = catBuilder.setDescription("various fruits").build();
        catBuilder = new Category.Builder("veggies");
        Category cat3 = catBuilder.setDescription("various veggies").build();

        Product.Builder pBuilder = new Product.Builder();
        pBuilder.setCategory(cat1)
                .setSupplier(sup1)
                .setQuantityPerUnit("10 bags")
                .setReorderLevel(10)
                .setUnitPrice(BigDecimal.valueOf(25.00))
                .setUnitsInStock(50)
                .setUnitsOnOrder(30);

        Product p1 = pBuilder.build("Fruits", 0);
        Product p2 = pBuilder.setCategory(cat2).setSupplier(sup2).build("FRUITS", 0);
        Product p3 = pBuilder.setCategory(cat3).setSupplier(sup3).build("Vegetables", 0);

        OrderDetailPk pk1 = new OrderDetailPk(order1, p1);
        OrderDetailPk pk2 = new OrderDetailPk(order2, p2);
        OrderDetailPk pk3 = new OrderDetailPk(order3, p3);

        od1 = new OrderDetail(pk1,
                BigDecimal.valueOf(22.5),
                Integer.valueOf(15),
                BigDecimal.valueOf(0));

        od2 = new OrderDetail(pk2,
                BigDecimal.valueOf(22.5),
                Integer.valueOf(15),
                BigDecimal.valueOf(0));

        od3 = new OrderDetail(pk3,
                BigDecimal.valueOf(22.5),
                Integer.valueOf(15),
                BigDecimal.valueOf(0));
    }

    @Test
    public void testHashCode() {
        assertThat(od1.hashCode(), is(od2.hashCode()));
        assertThat(od1.hashCode() == od3.hashCode(), is(false));
    }

    @Test
    public void testToString() {
        try {
            System.out.println("od1: " + od1);
        } catch (Exception e) {
            e.printStackTrace();
            fail("OrderDetail.toString threw an exception.");
        }
    }

    @Test
    public void testEqualsObject() {
        assertThat(od1.equals(od2), is(true));
        assertThat(od2.equals(od1), is(true));
        assertThat(od1.equals(od3), is(false));
        assertThat(od3.equals(od1), is(false));
    }

    @Test
    public void testCompareTo() {
        assertThat(od1.compareTo(od2), is(0));
        assertThat(od2.compareTo(od1), is(0));
        assertThat(od1.compareTo(od3), is(greaterThan(0)));
        assertThat(od3.compareTo(od1), is(lessThan(0)));
    }

}
