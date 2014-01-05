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
public class EmployeeTerritoryTest {

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

        EmployeeTerritory et1 = new EmployeeTerritory(pk1);
        EmployeeTerritory et2 = new EmployeeTerritory(pk2);
        EmployeeTerritory et3 = new EmployeeTerritory(pk3);

        assertThat(et1.hashCode(), is(et2.hashCode()));
        assertThat(et1.hashCode() == et3.hashCode(), is(false));
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
        EmployeeTerritory et1 = new EmployeeTerritory(pk1);
        try {
            System.out.println("et1: " + et1);
        } catch (Exception e) {
            e.printStackTrace();
            fail("EmployeeTerritory.toString threw exception");
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

        EmployeeTerritory et1 = new EmployeeTerritory(pk1);
        EmployeeTerritory et2 = new EmployeeTerritory(pk2);
        EmployeeTerritory et3 = new EmployeeTerritory(pk3);

        assertThat(et1.equals(et2), is(true));
        assertThat(et2.equals(et1), is(true));
        assertThat(et1.equals(et3), is(false));
        assertThat(et3.equals(et1), is(false));
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

        EmployeeTerritory et1 = new EmployeeTerritory(pk1);
        EmployeeTerritory et2 = new EmployeeTerritory(pk2);
        EmployeeTerritory et3 = new EmployeeTerritory(pk3);

        assertThat(et1.compareTo(et2), is(0));
        assertThat(et2.compareTo(et1), is(0));
        assertThat(et1.compareTo(et3) < 0, is(true));
        assertThat(et3.compareTo(et1) > 0, is(true));
    }

}
