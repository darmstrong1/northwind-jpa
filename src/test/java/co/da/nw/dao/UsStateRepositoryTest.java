package co.da.nw.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

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

import co.da.nw.dao.UsStateRepository;
import co.da.nw.domain.UsState;

import com.google.common.collect.ImmutableList;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={co.da.nw.config.ApplicationContext.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional()
public class UsStateRepositoryTest {

    @Autowired
    UsStateRepository repository;
    
    @Test
    public void testFindByStateName() {
        String stateNm = "Tennessee";
        UsState state = repository.findByStateName(stateNm);
        assertThat(state != null, is(true));
        assertThat(state.getStateName(), is(stateNm));
    }

    @Test
    public void testFindByStateAbbrev() {
        String stateAbbrev = "WY";
        UsState state = repository.findByStateAbbrev(stateAbbrev);
        assertThat(state != null, is(true));
        assertThat(state.getStateAbbrev(), is(stateAbbrev));
    }

    @Test
    public void testFindByStateRegion() {
        List<UsState> states = repository.findByStateRegion("south");
        assertThat(states != null, is(true));
        assertThat(states.size(), greaterThan(0));
        for(UsState state: states) assertThat(state.getStateRegion(), is("south"));
    }

    @Test
    public void testFindAll() {
        List<UsState> states = repository.findAll();
        assertThat(states != null, is(true));
        assertThat(states.size(), greaterThan(0));
    }

    @Test
    public void testFindAllSort() {
        List<UsState> states = repository.findAll(new Sort(Sort.Direction.ASC, "stateName", "stateAbbrev", "stateRegion"));
        assertThat(states != null, is(true));
        int size = states.size();
        assertThat(size, greaterThan(0));
        if(size > 1) {
            UsState prev = states.get(0);
            for(int i = 1; i < size; i++) {
                UsState current = states.get(i);
                // prev must be less than or equal to the current UsState.
                assertThat(prev.compareTo(current), is(lessThanOrEqualTo(0)));
                prev = current;
            }
        }
    }

    @Test
    public void testSaveIterableOfS() {
        UsState state1 = new UsState("Puerto Rico", "PR", "south");
        UsState state2 = new UsState("Virgin Islands", "VI", "south");
        
        ImmutableList<UsState> states = ImmutableList.of(state1, state2);
        
        ImmutableList<UsState> saved = ImmutableList.copyOf(repository.save(states));
        
        assertThat(states, is(saved));
    }

    @Test
    public void testSaveAndFlush() {
        UsState state1 = new UsState("Puerto Rico", "PR", "south");
        
        UsState saved = repository.saveAndFlush(state1);
        
        assertThat(state1, is(saved));
    }

}
