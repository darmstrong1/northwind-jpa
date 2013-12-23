package co.da.nw.domain;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { co.da.nw.config.ApplicationContext.class })
public class ProductTest {

    static Product p1;
    static Product p2;
    static Product p3;

    @BeforeClass
    public static void init() {
        Supplier.Builder sBuilder = new Supplier.Builder();
        sBuilder.setContactNm("Joe Supplier")
                .setAddress("10 2nd Street")
                .setCity("Lake Charles")
                .setRegion("LA")
                .setPostalCode("70601")
                .setCountry("US")
                .setPhone("337-111-1111");

        Supplier s1 = sBuilder.build("Joe's Supplies");
        Supplier s2 = sBuilder.setContactNm("jOE sUPPLIER").build("Joe's Supplies");
        Supplier s3 = sBuilder.setContactNm("Jake Fleming")
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

        Product.Builder builder = new Product.Builder();
        builder.setCategory(cat1)
                .setSupplier(s1)
                .setQuantityPerUnit("10 bags")
                .setReorderLevel(10)
                .setUnitPrice(BigDecimal.valueOf(25.00))
                .setUnitsInStock(50)
                .setUnitsOnOrder(30);

        p1 = builder.build("Fruits", 0);
        p2 = builder.setCategory(cat2).setSupplier(s2).build("FRUITS", 0);
        p3 = builder.setCategory(cat3).setSupplier(s3).build("Vegetables", 0);
    }

    @Test
    public void testHashCode() {
        assertThat(p1.hashCode(), is(p2.hashCode()));
        assertThat(p1.hashCode() != p3.hashCode(), is(true));
    }

    @Test
    public void testToString() {
        try {
            System.out.println("p1: " + p1);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Product.toString threw an Exception");
        }
    }

    @Test
    public void testEqualsObject() {
        assertThat(p1.equals(p2), is(true));
        assertThat(p2.equals(p1), is(true));
        assertThat(p1.equals(p3), is(false));
        assertThat(p3.equals(p1), is(false));
    }

    @Test
    public void testCompareTo() {
        assertThat(p1.compareTo(p2), is(0));
        assertThat(p2.compareTo(p1), is(0));
        assertThat(p1.compareTo(p3), is(lessThan(0)));
        assertThat(p3.compareTo(p1), is(greaterThan(0)));
    }

}
