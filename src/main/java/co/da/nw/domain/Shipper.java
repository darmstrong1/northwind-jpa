package co.da.nw.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

@Entity
@Table(name = "shippers")
public final class Shipper implements DomainObject, Comparable<Shipper> {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "shipper_id")
    private final Long id;

    @Column(name = "company_name")
    private final String companyName;

    @Column(name = "phone")
    private final String phone;

    @Transient
    private volatile int hashCode;

    /*
     * Hibernate must have a default constructor, but it's okay to make it private.
     */
    @SuppressWarnings("unused")
    private Shipper() {
        id = null;
        this.companyName = null;
        this.phone = null;
    }

    public Shipper(String companyName, String phone) {
        // companyName cannot be null.
        Preconditions.checkNotNull(companyName, "companyName must not be null.");
        id = null;
        this.companyName = companyName;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Shipper setCompanyName(String companyName) {
        return new Shipper(companyName, phone);
    }

    public String getPhone() {
        return phone;
    }

    public Shipper setPhone(String phone) {
        return new Shipper(companyName, phone);
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("id", id)
                .add("companyName", companyName)
                .add("phone", phone)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Shipper))
            return false;
        Shipper s = (Shipper) o;
        // Don't compare the id as one of the objects may not yet be persisted.
        return companyName.equalsIgnoreCase(s.companyName) &&
                (phone == null ? s.phone == null : phone.equalsIgnoreCase(s.phone));
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            // Because in equals, we compare ignoring case, we must convert the Strings to upper case
            // here so that the hash code will be the same if Strings are equal ignoring case.
            result = Objects.hash(companyName.toUpperCase(),
                    (phone == null ? phone : phone.toUpperCase()));
            hashCode = result;
        }
        return result;
    }

    @Override
    public int compareTo(Shipper o) {
        // Null is considered less than other values.
        if (o == null)
            return 1;
        return ComparisonChain.start()
                .compare(companyName, o.companyName, Ordering.from(String.CASE_INSENSITIVE_ORDER))
                .compare(phone, o.phone, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .result();
    }

}
