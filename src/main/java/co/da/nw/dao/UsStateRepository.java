package co.da.nw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.da.nw.domain.UsState;

public interface UsStateRepository extends JpaRepository<UsState, Long> {

    public UsState findByStateName(String stateName);
    public UsState findByStateAbbrev(String stateAbbrev);
    public List<UsState> findByStateRegion(String region);
    
}
