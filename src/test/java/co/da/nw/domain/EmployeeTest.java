package co.da.nw.domain;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
public class EmployeeTest {

    static Employee e1;
    static Employee e2;
    static Employee e3;

    @BeforeClass
    public static void initEmployees() {
        Employee.Builder builder = new Employee.Builder("Richards", "Jeff");
        builder.setTitle("Programmer")
                .setBirthDate(new LocalDateTime("1972-04-15"))
                .setHireDate(new LocalDateTime("2004-06-01"))
                .setAddress("35 San Gabriel Ct")
                .setCity("Old Hickory")
                .setRegion("TN")
                .setPostalCode("37138")
                .setCountry("USA")
                .setHomePhone("615-222-2222");

        e1 = builder.build();
        e2 = builder.build();
        e3 = builder.setHireDate(new LocalDateTime("2011-09-15")).build();
    }

    @Test
    public void testHashCode() {
        assertThat(e1.hashCode(), is(e2.hashCode()));
        assertTrue(e1.hashCode() != e3.hashCode());
    }

    @Test
    public void testToString() {
        try {
            System.out.println("e1: " + e1);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Employee.toString threw exception");
        }
    }

    @Test
    public void testEqualsObject() {
        assertThat(e1.equals(e2), is(true));
        assertThat(e1.equals(e3), is(false));
    }

    @Test
    public void testCompareTo() {
        assertThat(e1.compareTo(e2), is(0));
        assertThat(e1.compareTo(e3) < 0, is(true));
        assertThat(e3.compareTo(e1) > 0, is(true));
    }

}
