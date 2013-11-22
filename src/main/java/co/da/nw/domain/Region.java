package co.da.nw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;

import com.google.common.base.Preconditions;

@Entity
@Table( name = "region" )
@Immutable
public class Region implements DomainObject, Comparable<Region> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="region_id")
	private final Long regionId;
	
	@Column(name="region_description")
	private final String regionDesc;
	
    @Transient
    private volatile int hashCode;
	
	private Region() {
	    this("");
	}
	
	public Region(String regionDesc) {
	    Preconditions.checkNotNull(regionDesc, "regionDesc cannot be null");
		this.regionId = null;
		this.regionDesc = regionDesc;
	}
	
	public Long getRegionId() { return regionId; }
	public String getRegionDesc() { return regionDesc; }
	
	public Region setRegionDesc(String regionDesc) {
		return new Region(regionDesc);
	}
	
	@Override
	public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("regionId", regionId)
                .add("regionDesc", regionDesc)
                .toString();
	}
	
	@Override
	public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Region)) return false;
        Region r = (Region)o;
        
        return regionDesc.equalsIgnoreCase(r.regionDesc);
	}
	
	@Override
	public int hashCode() {
        int result = hashCode;
        if(result == 0) {
            // Because in equals, we compare ignoring case, we must convert the Strings to upper case
            // here so that the hash code will be the same if Strings are equal ignoring case.
            result = regionDesc.toUpperCase().hashCode();
            hashCode = result;
        }
        return result;
	}

    @Override
    public int compareTo(Region o) {
        return regionDesc.compareToIgnoreCase(o.regionDesc);
    }

}
