package co.da.nw.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

@Entity
@Table(name = "usstates")
public class UsState implements DomainObject, Comparable<UsState> {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "state_id")
    private final Integer stateId;

    @Column(name = "state_name")
    private final String stateName;

    @Column(name = "state_abbr")
    private final String stateAbbrev;

    @Column(name = "state_region")
    private final String stateRegion;

    @Transient
    private volatile int hashCode;

    private UsState() {
        this(null, null, null);
    }

    public UsState(String stateName,
            String stateAbbrev,
            String stateRegion) {

        stateId = null;
        this.stateName = stateName;
        this.stateAbbrev = stateAbbrev;
        this.stateRegion = stateRegion;
    }

    public String getStateName() {
        return stateName;
    }

    public String getStateAbbrev() {
        return stateAbbrev;
    }

    public String getStateRegion() {
        return stateRegion;
    }

    public UsState setStateName(String stateName) {
        return new UsState(stateName, stateAbbrev, stateRegion);
    }

    public UsState setStateAbbrev(String stateAbbrev) {
        return new UsState(stateName, stateAbbrev, stateRegion);
    }

    public UsState setStateRegion(String stateRegion) {
        return new UsState(stateName, stateAbbrev, stateRegion);
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("stateId", stateId)
                .add("stateName", stateName)
                .add("stateAbbrev", stateAbbrev)
                .add("stateRegion", stateRegion)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UsState))
            return false;
        UsState s = (UsState) o;

        return (stateName == null ? s.stateName == null : stateName.equalsIgnoreCase(s.stateName)) &&
                (stateAbbrev == null ? s.stateAbbrev == null : stateAbbrev.equalsIgnoreCase(s.stateAbbrev)) &&
                (stateRegion == null ? s.stateRegion == null : stateRegion.equalsIgnoreCase(s.stateRegion));
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            // Because in equals, we compare ignoring case, we must convert the Strings to upper case
            // here so that the hash code will be the same if Strings are equal ignoring case.
            result = Objects.hash((stateName == null ? stateName : stateName.toUpperCase()),
                    (stateAbbrev == null ? stateAbbrev : stateAbbrev.toUpperCase()),
                    (stateRegion == null ? stateRegion : stateRegion.toUpperCase()));

            hashCode = result;
        }

        return result;
    }

    @Override
    public int compareTo(UsState o) {
        int diff = ComparisonChain.start()
                .compare(stateName, o.stateName, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(stateAbbrev, o.stateAbbrev, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(stateRegion, o.stateRegion, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .result();

        return diff;
    }

}
