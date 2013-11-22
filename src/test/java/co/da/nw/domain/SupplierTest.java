package co.da.nw.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import co.da.nw.domain.Supplier;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
public class SupplierTest {

    static Supplier s1;
    static Supplier s2;
    static Supplier s3;
    
    @BeforeClass
    public static void init() {
        Supplier.Builder builder = new Supplier.Builder();
        builder.setContactNm("Joe Supplier")
            .setAddress("10 2nd Street")
            .setCity("Lake Charles")
            .setRegion("LA")
            .setPostalCode("70601")
            .setCountry("US")
            .setPhone("337-111-1111");
        
        s1 = builder.build("Joe's Supplies");
        s2 = builder.setContactNm("jOE sUPPLIER").build("Joe's Supplies");
        s3 = builder.setContactNm("Jake Fleming")
                .setAddress("2787 Holmes")
                .setCity("Memphis")
                .setRegion("TN")
                .setPostalCode("38111")
                .setPhone("901-111-1111")
                .build("Jake's Supplies");
    }
    
    @Test
    public void testHashCode() {
        assertThat(s1.hashCode(), is(s2.hashCode()));
        assertThat(s1.hashCode() != s3.hashCode(), is(true));
    }

    @Test
    public void testToString() {
        try { System.out.println("s1: " + s1);  } catch(Exception e) { e.printStackTrace(); fail("Supplier.toString threw an Exception"); }
    }

    @Test
    public void testEqualsObject() {
        assertThat(s1.equals(s2), is(true));
        assertThat(s2.equals(s1), is(true));
        assertThat(s1.equals(s3), is(false));
        assertThat(s3.equals(s1), is(false));
    }

    @Test
    public void testCompareTo() {
        assertThat(s1.compareTo(s2), is(0));
        assertThat(s2.compareTo(s1), is(0));
        assertThat(s1.compareTo(s3) > 0, is(true));
        assertThat(s3.compareTo(s1) < 0, is(true));
    }

}
