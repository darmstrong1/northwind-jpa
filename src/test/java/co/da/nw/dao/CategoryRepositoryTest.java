package co.da.nw.dao;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.da.nw.domain.Category;

import com.google.common.collect.ImmutableList;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { co.da.nw.config.ApplicationContext.class })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional()
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository repository;

    @Test
    public void testFindByCategoryName() {
        String name = "Produce";
        List<Category> categories = repository.findByCategoryName(name);
        assertThat(categories.size(), is(1));
        System.out.println(categories.get(0));
    }

    @Test
    public void testFindAll() {
        long count = repository.count();
        List<Category> categories = repository.findAll();
        assertThat(Long.valueOf(count), is(Long.valueOf(categories.size())));
    }

    @Test
    public void testFindAllSort() {
        long count = repository.count();
        List<Category> categories = repository.findAll(new Sort(Sort.Direction.DESC, "categoryName"));
        int size = categories.size();
        assertThat(count, is(Long.valueOf(size)));
        if (size > 1) {
            Category prev = categories.get(0);
            for (int i = 1; i < size; i++) {
                Category current = categories.get(i);
                // prev must be greater than or equal to the current Shipper.
                assertThat(prev.compareTo(current), is(greaterThanOrEqualTo(0)));
                prev = current;
            }

        }
    }

    @Test
    public void testSaveIterableOfS() {
        Category.Builder builder = new Category.Builder("fruits");
        Category cat1 = builder.setDescription("Various fruits").build();
        builder = new Category.Builder("pies");
        Category cat2 = builder.setDescription("Various pies").build();
        ImmutableList<Category> categories = ImmutableList.of(cat1, cat2);
        List<Category> savedCategories = repository.save(categories);
        assertThat(categories, is(savedCategories));
    }

    @Test
    public void testFlush() {
        Category.Builder builder = new Category.Builder("fruits");
        Category cat1 = builder.setDescription("Various fruits").build();
        repository.save(cat1);
        Long id = cat1.getCategoryId();
        repository.flush();
        cat1 = repository.findOne(id);
        assertTrue(cat1 != null);
    }

    @Test
    public void testSaveAndFlush() {
        Category.Builder builder = new Category.Builder("fruits");
        Category cat1 = builder.setDescription("Various fruits").build();
        repository.saveAndFlush(cat1);
        Long id = cat1.getCategoryId();
        cat1 = repository.findOne(id);
        assertTrue(cat1 != null);
    }

}
