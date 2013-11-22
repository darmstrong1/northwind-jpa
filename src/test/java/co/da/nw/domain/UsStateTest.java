package co.da.nw.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import co.da.nw.domain.UsState;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
public class UsStateTest {
    
    static UsState state1;
    static UsState state2;
    static UsState state3;
    
    @BeforeClass
    public static void init() {
        state1 = new UsState("Tennessee", "TN", "south");
        state2 = new UsState("TENNESSEE", "tn", "SOUTH");
        state3 = new UsState("Utah", "UT", "west");
    }

    @Test
    public void testHashCode() {
        assertThat(state1.hashCode(), is(state2.hashCode()));
        assertThat(state1.hashCode() != state3.hashCode(), is(true));
    }

    @Test
    public void testToString() {
        try { System.out.println("state1: " + state1); } catch(Exception e) { e.printStackTrace(); fail("UsState.toString() threw exception"); }
    }

    @Test
    public void testEqualsObject() {
        assertThat(state1.equals(state2), is(true));
        assertThat(state2.equals(state1), is(true));
        assertThat(state1.equals(state3), is(false));
        assertThat(state3.equals(state1), is(false));
    }

    @Test
    public void testCompareTo() {
        assertThat(state1.compareTo(state2), is(0));
        assertThat(state2.compareTo(state1), is(0));
        assertThat(state1.compareTo(state3), lessThan(0));
        assertThat(state3.compareTo(state1), greaterThan(0));
    }

}
