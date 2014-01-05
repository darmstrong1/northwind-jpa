package co.da.nw.domain;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { co.da.nw.config.ApplicationContext.class })
public class EmployeeTerritoryPkTest {

    @Test
    public void testHashCode() {
        Employee.Builder builder = new Employee.Builder("Douglas", "Frank");
        builder.setTitle("Programmer")
                .setBirthDate(new LocalDateTime("1972-04-15"))
                .setHireDate(new LocalDateTime("2004-06-01"))
                .setAddress("35 San Gabriel Ct")
                .setCity("Old Hickory")
                .setRegion("TN")
                .setPostalCode("37138")
                .setCountry("USA")
                .setHomePhone("615-222-2222");

        Employee e1 = builder.build();

        Territory t1 = new Territory("111111", "Nashville", new Region("Southern"));

        EmployeeTerritoryPk pk1 = new EmployeeTerritoryPk(e1, t1);
        EmployeeTerritoryPk pk2 = new EmployeeTerritoryPk(e1, t1);

        builder = new Employee.Builder("Turner", "Susan");
        Employee e2 = builder.setTitle("Sales Representative").build();

        EmployeeTerritoryPk pk3 = new EmployeeTerritoryPk(e2, t1);

        assertThat(pk1.hashCode(), is(pk2.hashCode()));
        assertThat(pk1.hashCode() == pk3.hashCode(), is(false));
    }

    @Test
    public void testToString() {
        Employee.Builder builder = new Employee.Builder("Douglas", "Frank");
        builder.setTitle("Programmer")
                .setBirthDate(new LocalDateTime("1972-04-15"))
                .setHireDate(new LocalDateTime("2004-06-01"))
                .setAddress("35 San Gabriel Ct")
                .setCity("Old Hickory")
                .setRegion("TN")
                .setPostalCode("37138")
                .setCountry("USA")
                .setHomePhone("615-222-2222");

        Employee e1 = builder.build();

        Territory t1 = new Territory("111111", "Nashville", new Region("Southern"));

        EmployeeTerritoryPk pk1 = new EmployeeTerritoryPk(e1, t1);

        try {
            System.out.println("pk1: " + pk1);
        } catch (Exception e) {
            e.printStackTrace();
            fail("EmployeeTerritoryPk.toString threw exception");
        }
    }

    @Test
    public void testEqualsObject() {
        Employee.Builder builder = new Employee.Builder("Douglas", "Frank");
        builder.setTitle("Programmer")
                .setBirthDate(new LocalDateTime("1972-04-15"))
                .setHireDate(new LocalDateTime("2004-06-01"))
                .setAddress("35 San Gabriel Ct")
                .setCity("Old Hickory")
                .setRegion("TN")
                .setPostalCode("37138")
                .setCountry("USA")
                .setHomePhone("615-222-2222");

        Employee e1 = builder.build();

        Territory t1 = new Territory("111111", "Nashville", new Region("Southern"));

        EmployeeTerritoryPk pk1 = new EmployeeTerritoryPk(e1, t1);
        EmployeeTerritoryPk pk2 = new EmployeeTerritoryPk(e1, t1);

        builder = new Employee.Builder("Turner", "Susan");
        Employee e2 = builder.setTitle("Sales Representative").build();

        EmployeeTerritoryPk pk3 = new EmployeeTerritoryPk(e2, t1);

        assertThat(pk1.equals(pk2), is(true));
        assertThat(pk2.equals(pk1), is(true));
        assertThat(pk1.equals(pk3), is(false));
        assertThat(pk3.equals(pk1), is(false));
    }

    @Test
    public void testCompareTo() {
        Employee.Builder builder = new Employee.Builder("Douglas", "Frank");
        builder.setTitle("Programmer")
                .setBirthDate(new LocalDateTime("1972-04-15"))
                .setHireDate(new LocalDateTime("2004-06-01"))
                .setAddress("35 San Gabriel Ct")
                .setCity("Old Hickory")
                .setRegion("TN")
                .setPostalCode("37138")
                .setCountry("USA")
                .setHomePhone("615-222-2222");

        Employee e1 = builder.build();

        Territory t1 = new Territory("111111", "Nashville", new Region("Southern"));

        EmployeeTerritoryPk pk1 = new EmployeeTerritoryPk(e1, t1);
        EmployeeTerritoryPk pk2 = new EmployeeTerritoryPk(e1, t1);

        builder = new Employee.Builder("Turner", "Susan");
        Employee e2 = builder.setTitle("Sales Representative").build();

        EmployeeTerritoryPk pk3 = new EmployeeTerritoryPk(e2, t1);

        assertThat(pk1.compareTo(pk2), is(0));
        assertThat(pk2.compareTo(pk1), is(0));
        assertThat(pk1.compareTo(pk3) < 0, is(true));
        assertThat(pk3.compareTo(pk1) > 0, is(true));
    }

}
