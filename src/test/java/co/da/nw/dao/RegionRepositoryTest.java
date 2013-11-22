package co.da.nw.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import co.da.nw.dao.RegionRepository;
import co.da.nw.domain.Region;
import co.da.nw.domain.Region_;

import com.google.common.collect.ImmutableList;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional()
public class RegionRepositoryTest {

    @Autowired
    RegionRepository repository;
    
    @Autowired
    LocalContainerEntityManagerFactoryBean entityManagerFactory;
    
    
    @Test
    public void testFindByRegionDesc() {
        String desc = "Southern";
        List<Region> regions = repository.findByRegionDesc(desc);
        assertThat(regions.size(), is(1));
        assertThat(regions.get(0).getRegionDesc(), is(desc));
    }

    @Test
    public void testFindAllSort() {
        List<Region> regions = repository.findAll(new Sort(Sort.Direction.ASC, "regionDesc"));
        long count = repository.count();
        assertThat(Long.valueOf(regions.size()), is(count));
        int size = regions.size();
        assertThat(Long.valueOf(count), is(Long.valueOf(size)));
        if(size > 1) {
            Region prev = regions.get(0);
            for(int i = 1; i < size; i++) {
                Region current = regions.get(i);
                // prev must be greater than or equal to the current Region.
                assertThat(prev.compareTo(current), is(lessThanOrEqualTo(0)));
                prev = current;
            }
        }
    }

    @Test
    public void testSaveIterableOfS() {
        Region r1 = new Region("Southeastern");
        Region r2 = new Region("Northwestern");
        ImmutableList<Region> regions = ImmutableList.of(r1, r2);
        List<Region> saved = repository.save(regions);
        
        assertThat(regions, is(saved));
    }

    @Test
    public void testSaveAndFlush() {
        Region r1 = new Region("Southeastern");
        repository.saveAndFlush(r1);
        Long id = r1.getRegionId();
        Region saved = repository.findOne(id);
        assertThat(saved, is(r1));
//        
//        EntityManager entityManager = entityManagerFactory.getObject().createEntityManager();
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Region> query = cb.createQuery(Region.class);
//        Root<Region> root = query.from(Region.class);
//        Path<String> descPath = root.get(Region_.regionDesc);
//        query.where(cb.equal(descPath, "Southern"));
//        
//        Region result = entityManager.createQuery(query).getSingleResult();
        
    }

}
