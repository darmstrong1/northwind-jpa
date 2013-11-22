package co.da.nw.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import co.da.nw.domain.Employee;
import co.da.nw.domain.EmployeeTerritory;
import co.da.nw.domain.EmployeeTerritoryPk;
import co.da.nw.domain.EmployeeTerritoryPk_;
import co.da.nw.domain.EmployeeTerritory_;
import co.da.nw.domain.Territory;

public class EmployeeTerritorySpecifications {

    public static Specification<EmployeeTerritory> employeeIs(final Employee employee) {
        return new Specification<EmployeeTerritory>() {

            @Override
            public Predicate toPredicate(Root<EmployeeTerritory> etRoot,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<EmployeeTerritoryPk> pkPath = etRoot.<EmployeeTerritoryPk>get(EmployeeTerritory_.pk);
                Path<Employee> empPath = pkPath.get(EmployeeTerritoryPk_.employee);
                return cb.equal(empPath, employee);
            }
        };
    }

    public static Specification<EmployeeTerritory> territoryIs(final Territory territory) {
        return new Specification<EmployeeTerritory>() {

            @Override
            public Predicate toPredicate(Root<EmployeeTerritory> etRoot,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<EmployeeTerritoryPk> pkPath = etRoot.get(EmployeeTerritory_.pk);
                Path<Territory> tPath = pkPath.get(EmployeeTerritoryPk_.territory);
                return cb.equal(tPath, territory);
            }
        };
    }
}
