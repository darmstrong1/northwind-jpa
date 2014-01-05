package co.da.nw.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.Preconditions;

@Entity
@Table(name = "employeeterritories")
public final class EmployeeTerritory implements DomainObject, Comparable<EmployeeTerritory> {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Id
    private final EmployeeTerritoryPk pk;

    @Transient
    private volatile int hashCode;

    private EmployeeTerritory() {
        this(new EmployeeTerritoryPk(new Employee.Builder("", "").build(), new Territory("", "", new Region(""))));
    }

    public EmployeeTerritory(EmployeeTerritoryPk pk) {
        Preconditions.checkNotNull(pk, "pk cannot be null.");
        this.pk = pk;
    }

    public EmployeeTerritoryPk getPk() {
        return pk;
    }

    public EmployeeTerritory setPk(EmployeeTerritoryPk pk) {
        return new EmployeeTerritory(pk);
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("pk", pk)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EmployeeTerritory))
            return false;
        EmployeeTerritory t = (EmployeeTerritory) o;

        return pk.equals(t.pk);
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = pk.hashCode();
            hashCode = result;
        }
        return result;
    }

    @Override
    public int compareTo(EmployeeTerritory o) {
        return pk.compareTo(o.pk);
    }

}
