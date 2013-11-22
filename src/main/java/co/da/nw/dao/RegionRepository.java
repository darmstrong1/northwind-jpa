package co.da.nw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.da.nw.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
    
    public List<Region> findByRegionDesc(String regionDesc);

}
