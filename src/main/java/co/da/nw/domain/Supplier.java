package co.da.nw.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;

import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

@Entity
@Table( name = "suppliers" )
@Immutable
public class Supplier implements DomainObject, Comparable<Supplier> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static class Builder {
		
		private String contactNm;
		private String contactTitle;
		private String address;
		private String city;
		private String region;
		private String postalCode;
		private String country;
		private String phone;
		private String fax;
		private String homePage;
		
		public Builder setContactNm(String contactNm) {
			this.contactNm = contactNm;
			return this;
		}
		
		public Builder setContactTitle(String contactTitle) {
			this.contactTitle = contactTitle;
			return this;
		}
		
		public Builder setAddress(String address) {
			this.address = address;
			return this;
		}
		
		public Builder setCity(String city) {
			this.city = city;
			return this;
		}
		
		public Builder setRegion(String region) {
			this.region = region;
			return this;
		}
		
		public Builder setPostalCode(String postalCode) {
			this.postalCode = postalCode;
			return this;
		}
		
		public Builder setCountry(String country) {
			this.country = country;
			return this;
		}
		
		public Builder setPhone(String phone) {
			this.phone = phone;
			return this;
		}
		
		public Builder setFax(String fax) {
			this.fax = fax;
			return this;
		}
		
		public Builder setHomePage(String homePage) {
			this.homePage = homePage;
			return this;
		}
		
		public Supplier build(String companyNm) {
			return new Supplier(companyNm,
					contactNm,
					contactTitle,
					address,
					city,
					region,
					postalCode,
					country,
					phone,
					fax,
					homePage);
		}
		
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="supplier_id")
	private final Long supplierId;
	
	@Column(name="company_name")
	private final String companyNm;
	
	@Column(name="contact_name")
	private final String contactNm;
	
	@Column(name="contact_title")
	private final String contactTitle;
	
	@Column(name="address")
	private final String address;
	
	@Column(name="city")
	private final String city;
	
	@Column(name="region")
	private final String region;
	
	@Column(name="postal_code")
	private final String postalCode;
	
	@Column(name="country")
	private final String country;
	
	@Column(name="phone")
	private final String phone;
	
	@Column(name="fax")
	private final String fax;
	
	@Column(name="home_page")
	private final String homePage;
    
    @Transient
    private volatile int hashCode;
	
	private Supplier() {
	    this("",
	         null,
	         null,
	         null,
	         null,
	         null,
	         null,
	         null,
	         null,
	         null,
	         null);
	}
	
	private Supplier(String companyNm,
			String contactNm,
			String contactTitle,
			String address,
			String city,
			String region,
			String postalCode,
			String country,
			String phone,
			String fax,
			String homePage) {
		
	    Preconditions.checkNotNull(companyNm, "companyNm must not be null.");
		supplierId = null;
		this.companyNm = companyNm;
		this.contactNm = contactNm;
		this.contactTitle = contactTitle;
		this.address = address;
		this.city = city;
		this.region = region;
		this.postalCode = postalCode;
		this.country = country;
		this.phone = phone;
		this.fax = fax;
		this.homePage = homePage;
	}
	
	public Long getSupplierId() { return supplierId; }
	public String getCompanyNm() { return companyNm; }
	public String getContactNm() { return contactNm; }
	public String getAddress() { return address; }
	public String getCity() { return city; }
	public String getRegion() { return region; }
	public String getPostalCode() { return postalCode; }
	public String getCountry() { return country; }
	public String getPhone() { return phone; }
	public String getFax() { return fax; }
	public String getHomePage() { return homePage; }
	
	public Supplier setCompanyNm(String companyNm) {
		return new Supplier(companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax,
				homePage);
	}
	
	public Supplier setContactNm(String contactNm) {
		return new Supplier(companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax,
				homePage);
	}
	
	public Supplier setContactTitle(String contactTitle) {
		return new Supplier(companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax,
				homePage);
	}
	
	public Supplier setAddress(String address) {
		return new Supplier(companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax,
				homePage);
	}
	
	public Supplier setCity(String city) {
		return new Supplier(companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax,
				homePage);
	}
	
	public Supplier setRegion(String region) {
		return new Supplier(companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax,
				homePage);
	}
	
	public Supplier setPostalCode(String postalCode) {
		return new Supplier(companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax,
				homePage);
	}
	
	public Supplier setCountry(String country) {
		return new Supplier(companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax,
				homePage);
	}
	
	public Supplier setPhone(String phone) {
		return new Supplier(companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax,
				homePage);
	}
	
	public Supplier setFax(String fax) {
		return new Supplier(companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax,
				homePage);
	}
	
	public Supplier setHomePage(String homePage) {
		return new Supplier(companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax,
				homePage);
	}
	
	@Override
	public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("supplierId", supplierId)
                .add("companyNm", companyNm)
                .add("contactNm", contactNm)
                .add("contactTitle", contactTitle)
                .add("address", address)
                .add("city", city)
                .add("region", region)
                .add("postalCode", postalCode)
                .add("country", country)
                .add("phone", phone)
                .add("fax", fax)
                .add("homePage", homePage)
                .toString();
	}
	
	@Override
	public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Supplier)) return false;
        Supplier s = (Supplier)o;
	    
        return companyNm.equalsIgnoreCase(s.companyNm) &&
                (contactNm == null ? s.contactNm == null : contactNm.equalsIgnoreCase(s.contactNm)) &&
                (contactTitle == null ? s.contactTitle == null : contactTitle.equalsIgnoreCase(s.contactTitle)) &&
                (address == null ? s.address == null : address.equalsIgnoreCase(s.address)) &&
                (city == null ? s.city == null : city.equalsIgnoreCase(s.city)) &&
                (region == null ? s.region == null : region.equalsIgnoreCase(s.region)) &&
                (postalCode == null ? s.postalCode == null : postalCode.equalsIgnoreCase(s.postalCode)) &&
                (country == null ? s.country == null : country.equalsIgnoreCase(s.country)) &&
                (phone == null ? s.phone == null : phone.equalsIgnoreCase(s.phone)) &&
                (fax == null ? s.fax == null : fax.equalsIgnoreCase(s.fax)) &&
                (homePage == null ? s.homePage == null : homePage.equalsIgnoreCase(s.homePage));
	}
	
	@Override
	public int hashCode() {
        int result = hashCode;
        if(result == 0) {
            // Because in equals, we compare ignoring case, we must convert the Strings to upper case
            // here so that the hash code will be the same if Strings are equal ignoring case.
            result = Objects.hash(
                    companyNm.toUpperCase(),
                    (contactTitle == null ? contactTitle : contactTitle.toUpperCase()),
                    (address == null ? address : address.toUpperCase()),
                    (city == null ? city : city.toUpperCase()),
                    (region == null ? region : region.toUpperCase()),
                    (postalCode == null ? postalCode : postalCode.toUpperCase()),
                    (country == null ? country : country.toUpperCase()),
                    (phone == null ? phone : phone.toUpperCase()),
                    (fax == null ? fax : fax.toUpperCase()),
                    (homePage == null ? homePage : homePage.toUpperCase()));
                    
            hashCode = result;
        }
        return result;
	}

    @Override
    public int compareTo(Supplier o) {
        int diff = ComparisonChain.start()
                .compare(companyNm, o.companyNm, Ordering.from(String.CASE_INSENSITIVE_ORDER))
                .compare(contactTitle, o.contactTitle, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(address, o.address, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(city, o.city, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(region, o.region, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(postalCode, o.postalCode, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(country, o.country, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(phone, o.phone, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(fax, o.fax, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(homePage, o.homePage, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .result();
        
        return diff;
    }
	

}
