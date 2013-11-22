package co.da.nw.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EmployeeTerritoryPk.class)
public abstract class EmployeeTerritoryPk_ {
    public static volatile SingularAttribute<EmployeeTerritoryPk, Employee> employee;
    public static volatile SingularAttribute<EmployeeTerritoryPk, Territory> territory;

}
