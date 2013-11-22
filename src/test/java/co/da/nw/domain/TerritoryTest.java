package co.da.nw.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import co.da.nw.domain.Region;
import co.da.nw.domain.Territory;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
public class TerritoryTest {

    @Test
    public void testHashCode() {
        Territory t1 = new Territory("111111", "Memphis", new Region("Southern"));
        Territory t2 = new Territory("111111", "MEMPHIS", new Region("southern"));
        Territory t3 = new Territory("22222", "Seattle", new Region("Western"));
        
        assertThat(t1.hashCode(), is(t2.hashCode()));
        assertThat(t1.hashCode() == t3.hashCode(), is(false));
    }

    @Test
    public void testToString() {
        Territory t1 = new Territory("111111", "Memphis", new Region("Southern"));
        try { System.out.println("t1: " + t1); } catch(Exception e) { e.printStackTrace(); fail("Territory.toString threw exception"); }
    }

    @Test
    public void testEqualsObject() {
        Territory t1 = new Territory("111111", "Memphis", new Region("Southern"));
        Territory t2 = new Territory("111111", "MEMPHIS", new Region("southern"));
        Territory t3 = new Territory("22222", "Seattle", new Region("Western"));
        
        assertThat(t1.equals(t2), is(true));
        assertThat(t2.equals(t1), is(true));
        assertThat(t1.equals(t3), is(false));
        assertThat(t3.equals(t1), is(false));
    }

    @Test
    public void testCompareTo() {
        Territory t1 = new Territory("111111", "Memphis", new Region("Southern"));
        Territory t2 = new Territory("111111", "MEMPHIS", new Region("southern"));
        Territory t3 = new Territory("22222", "Seattle", new Region("Western"));
        
        assertThat(t1.compareTo(t2), is(0));
        assertThat(t2.compareTo(t1), is(0));
        assertThat(t1.compareTo(t3) < 0, is(true));
        assertThat(t3.compareTo(t1) > 0, is(true));
    }

}
