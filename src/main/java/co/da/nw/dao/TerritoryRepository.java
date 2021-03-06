package co.da.nw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.da.nw.domain.Territory;

public interface TerritoryRepository extends JpaRepository<Territory, String> {
    
    public List<Territory> findByTerritoryDesc(String territoryDesc);

}
