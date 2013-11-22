package co.da.nw.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import co.da.nw.domain.EmployeeTerritory;
import co.da.nw.domain.EmployeeTerritoryPk;

public interface EmployeeTerritoryRepository extends JpaRepository<EmployeeTerritory, EmployeeTerritoryPk>,
    JpaSpecificationExecutor<EmployeeTerritory>, QueryDslPredicateExecutor<EmployeeTerritory> {

}
