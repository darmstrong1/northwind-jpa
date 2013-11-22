package co.da.nw.domain;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import co.da.nw.domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
public class CategoryTest {

	@Test
	public void testHashCode() {
		Category.Builder builder = new Category.Builder();
		Category cat1 = builder.setDescription("various fruits").build("fruits");
		Category cat2 = builder.setDescription("various fruits").build("FRUITS"); // Test that it is case insensitive.
		assertThat(cat1.hashCode(), is(cat2.hashCode()));
	}

	@Test
	public void testToString() {
		Category.Builder builder = new Category.Builder();
		Category cat1 = builder.setDescription("various fruits").build("fruits");
		try {
			System.out.println("cat1: " + cat1);
		} catch(Exception e) {
			fail("toString threw exception");
		}
	}

	@Test
	public void testEqualsObject() {
		Category.Builder builder = new Category.Builder();
		Category cat1 = builder.setDescription("various fruits").build("fruits");
		Category cat2 = builder.setDescription("various fruits").build("fruits");
		Category cat3 = builder.setDescription("various veggies").build("veggies");
		Category cat4 = null;
		assertThat(cat1.equals(cat2), is(true));
		assertThat(cat1.equals(cat3), is(false));
		assertThat(cat1.equals(cat4), is(false));
		assertThat(cat1.equals("not even a Category"), is(false));
	}

	@Test
	public void testCompareTo() {
		Category.Builder builder = new Category.Builder();
		Category cat1 = builder.setDescription("various fruits").build("fruits");
		Category cat2 = builder.setDescription("various fruits").build("fruits");
		Category cat3 = builder.setDescription("various veggies").build("veggies");
		Category cat4 = null;
		assertThat(cat1.compareTo(cat2), is(0));
		assertTrue(cat1.compareTo(cat3) <= -1);
		assertTrue(cat1.compareTo(cat4) >= 1);
	}

}
