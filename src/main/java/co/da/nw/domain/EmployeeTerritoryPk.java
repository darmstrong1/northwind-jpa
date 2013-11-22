package co.da.nw.domain;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;

@Embeddable
public final class EmployeeTerritoryPk implements DomainObject, Comparable<EmployeeTerritoryPk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn(name="employee_id")
    private final Employee employee;
	
	@ManyToOne
	@JoinColumn(name="territory_id")
	private final Territory territory;
    
    @Transient
    private volatile int hashCode;
	
	private EmployeeTerritoryPk() {
	    this( new Employee.Builder().build("", ""),
	           new Territory("", "", new Region("")));
	}
	
	public EmployeeTerritoryPk(Employee emp, Territory territory) {
	    Preconditions.checkNotNull(territory, "territory cannot be null.");
        Preconditions.checkNotNull(emp, "emp cannot be null.");
		this.territory = territory;
		employee = emp;
	}
	
	public Territory getTerritory() { return territory; }
	public Employee getEmployee() { return employee; }
	
	public EmployeeTerritoryPk setEmployee(Employee emp) {
		return new EmployeeTerritoryPk(emp, territory);
	}
	
	public EmployeeTerritoryPk setTerritory(Territory territory) {
		return new EmployeeTerritoryPk(employee, territory);
	}
	
	@Override
	public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("employee", employee)
                .add("territory", territory)
                .toString();
	}
	
	@Override
	public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof EmployeeTerritoryPk)) return false;
        EmployeeTerritoryPk t = (EmployeeTerritoryPk)o;
	    
        return employee.equals(t.employee) &&
                territory.equals(t.territory);
	}
	
	@Override
	public int hashCode() {
        int result = hashCode;
        if(result == 0) {
            result = Objects.hash(employee, territory);
            hashCode = result;
        }
        
        return result;
	}

    @Override
    public int compareTo(EmployeeTerritoryPk o) {
        int diff = ComparisonChain.start()
                .compare(employee, o.employee)
                .compare(territory, o.territory)
                .result();
        
        return diff;
    }

}
