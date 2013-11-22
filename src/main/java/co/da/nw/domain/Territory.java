package co.da.nw.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

@Entity
@Table( name = "territories" )
@Immutable
public class Territory implements DomainObject, Comparable<Territory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="territory_id")
	private final String territoryId;
	
	@Column(name="territory_description")
	private final String territoryDesc;
	
	@ManyToOne
	@JoinColumn(name="region_id")
	private final Region region;
	
    @Transient
    private volatile int hashCode;
	
	private Territory() {
		this("", "", new Region(""));
	}
	
	public Territory(String territoryId,
			String territoryDesc,
			Region region) {
		
	    Preconditions.checkNotNull(territoryId, "territoryId cannot be null");
	    Preconditions.checkNotNull(territoryDesc, "territoryDesc cannot be null.");
	    Preconditions.checkNotNull(region, "region cannot be null.");
		this.territoryId = territoryId;
		this.territoryDesc = territoryDesc;
		this.region = region;
	}
	
	public String getTerritoryId() { return territoryId; }
	public String getTerritoryDesc() { return territoryDesc; }
	public Region getRegion() { return region; }
	
	public Territory setTerritoryId(String territoryId) {
		return new Territory(territoryId, territoryDesc, region);
	}
	
	public Territory setTerritoryDesc(String territoryDesc) {
		return new Territory(territoryId, territoryDesc, region);
	}
	
	public Territory setRegion(Region region) {
		return new Territory(territoryId, territoryDesc, region);
	}
	
	@Override
	public String toString() {
	    return com.google.common.base.Objects.toStringHelper(this)
	            .add("territoryId", territoryId)
	            .add("territoryDesc", territoryDesc)
	            .add("region", region)
	            .toString();
	}
    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Territory)) return false;
        Territory t = (Territory)o;
        
        return  territoryId.equalsIgnoreCase(t.territoryId) &&
                territoryDesc.equalsIgnoreCase(t.territoryDesc) &&
                region.equals(t.region);
    }
    
    @Override
    public int hashCode() {
        int result = hashCode;
        if(result == 0) {
            // Because in equals, we compare ignoring case, we must convert the Strings to upper case
            // here so that the hash code will be the same if Strings are equal ignoring case.
            result = Objects.hash(
                    territoryId.toUpperCase(),
                    territoryDesc.toUpperCase(),
                    region.hashCode());
            
            hashCode = result;
        }
        return result;
    }

    @Override
    public int compareTo(Territory o) {
        int diff = ComparisonChain.start()
                .compare(territoryId, o.territoryId, Ordering.from(String.CASE_INSENSITIVE_ORDER))
                .compare(territoryDesc, o.territoryDesc, Ordering.from(String.CASE_INSENSITIVE_ORDER))
                .result();
        
        if(diff != 0) return diff;
        return region.compareTo(o.region);
    }
}
