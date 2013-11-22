package co.da.nw.dao;

import co.da.nw.domain.Employee;
import co.da.nw.domain.Territory;

import com.mysema.query.types.Predicate;
import co.da.nw.domain.QEmployeeTerritory;

public class EmployeeTerritoryPredicates {
    
    public static Predicate employeeIs(Employee employee) {
        QEmployeeTerritory et = QEmployeeTerritory.employeeTerritory;
        return et.pk.employee.eq(employee);
    }
    
    public static Predicate territoryIs(Territory territory) {
        QEmployeeTerritory et = QEmployeeTerritory.employeeTerritory;
        return et.pk.territory.eq(territory);
    }

}
