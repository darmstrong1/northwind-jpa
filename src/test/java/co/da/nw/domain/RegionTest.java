package co.da.nw.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import co.da.nw.domain.Region;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
public class RegionTest {

    @Test
    public void testHashCode() {
        Region r1 = new Region("Southern");
        Region r2 = new Region("Southern");
        Region r3 = new Region("SOUTHERN");
        Region r4 = new Region("Western");
        
        assertThat(r1.hashCode(), is(r2.hashCode()));
        assertThat(r1.hashCode(), is(r3.hashCode()));
        assertThat(r1.hashCode() == r4.hashCode(), is(false));
    }

    @Test
    public void testToString() {
        Region r1 = new Region("Southern");
        try { System.out.println("r1: " + r1); } catch(Exception e) { e.printStackTrace(); fail("Region.toString threw exception"); }
    }

    @Test
    public void testEqualsObject() {
        Region r1 = new Region("Southern");
        Region r2 = new Region("Southern");
        Region r3 = new Region("SOUTHERN");
        Region r4 = new Region("Western");
        
        assertThat(r1.equals(r2), is(true));
        assertThat(r1.equals(r3), is(true));
        assertThat(r3.equals(r1), is(true));
        assertThat(r1.equals(r4), is(false));
        assertThat(r4.equals(r1), is(false));
    }

    @Test
    public void testCompareTo() {
        Region r1 = new Region("Southern");
        Region r2 = new Region("Southern");
        Region r3 = new Region("SOUTHERN");
        Region r4 = new Region("Western");
        
        assertThat(r1.compareTo(r2), is(0));
        assertThat(r1.compareTo(r3), is(0));
        assertThat(r3.compareTo(r1), is(0));
        assertThat(r1.compareTo(r4) < 0, is(true));
        assertThat(r4.compareTo(r1) > 0, is(true));
    }

}
