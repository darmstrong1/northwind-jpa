package co.da.nw.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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

import co.da.nw.dao.RegionRepository;
import co.da.nw.dao.TerritoryRepository;
import co.da.nw.domain.Region;
import co.da.nw.domain.Territory;

import com.google.common.collect.ImmutableList;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional()
public class TerritoryRepositoryTest {

    @Autowired
    TerritoryRepository repository;
    
    @Autowired
    RegionRepository rgnRepository;
    
    @Test
    public void testFindByTerritoryDesc() {
        String territoryDesc = "Boston";
        List<Territory> territories = repository.findByTerritoryDesc(territoryDesc);
        assertThat(territories.size(), is(1));
        assertThat(territories.get(0).getTerritoryDesc(), is(territoryDesc));
    }

    @Test
    public void testFindAllSort() {
        List<Territory> territories = repository.findAll(new Sort(Sort.Direction.ASC, "territoryId"));
        long count = repository.count();
        assertThat(Long.valueOf(territories.size()), is(count));
        int size = territories.size();
        assertThat(Long.valueOf(count), is(Long.valueOf(size)));
        if(size > 1) {
            Territory prev = territories.get(0);
            for(int i = 1; i < size; i++) {
                Territory current = territories.get(i);
                // prev must be greater than or equal to the current Region.
                assertThat(prev.compareTo(current), is(lessThanOrEqualTo(0)));
                prev = current;
            }
        }
    }

    @Test
    public void testSaveIterableOfS() {
        String desc = "Southern";
        List<Region> south = rgnRepository.findByRegionDesc(desc);
        assertThat(south.size(), is(1));
        assertThat(south.get(0).getRegionDesc(), is(desc));
        
        Territory t1 = new Territory("11111", "Memphis", south.get(0));
        
        desc = "Western";
        List<Region> west = rgnRepository.findByRegionDesc(desc);
        assertThat(west.size(), is(1));
        assertThat(west.get(0).getRegionDesc(), is(desc));
        
        Territory t2 = new Territory("22222", "Seattle", west.get(0));
        ImmutableList<Territory> territories = ImmutableList.of(t1, t2);
        List<Territory> saved = repository.save(territories);
        assertThat(territories, is(saved));
    }

    @Test
    public void testSaveAndFlush() {
        Region northWest = new Region("Northwestern");
        
        Territory t = new Territory("02222", "Seattle", northWest);
        
        rgnRepository.save(northWest);
        
        Territory saved = repository.saveAndFlush(t);
        
        assertThat(t, is(saved));
        
    }
    
    @Test
    public void testFindOne() {
        Territory saved = repository.findOne("01581");
        
        Region region = rgnRepository.findOne(1L);
        
        Territory created = new Territory("01581", "Westboro", region);
        
        assertThat(saved, is(created));
    }

}
