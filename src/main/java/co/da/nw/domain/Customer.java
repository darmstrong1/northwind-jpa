package co.da.nw.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Immutable;

import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

@Entity
@Table( name = "customers" )
@Immutable
public final class Customer implements DomainObject, Comparable<Customer> {

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
		
		public Builder setContactNm(String contactNm) {
			this.contactNm = contactNm;
			return this;
		}
		
		public Builder setContactTitle(String title) {
			this.contactTitle = title;
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
		
		public Builder setPostalCode(String postalCd) {
			postalCode = postalCd;
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
		
		public Customer build(String customerId, String companyNm) {
			return new Customer(customerId,
					companyNm,
					contactNm,
					contactTitle,
					address,
					city,
					region,
					postalCode,
					country,
					phone,
					fax);
		}
	}

	@Id
	@Column(name="customer_id", columnDefinition="bpchar")
	private final String customerId;
	
	@Column(name="company_name")
	private final String companyNm;
	
	@Column(name="contact_name")
	private final String contactNm;
	
	@Column(name="contact_title")
	private final String contactTitle;
	
	@Column
	private final String address;
	
	@Column
	private final String city;
	
	@Column
	private final String region;
	
	@Column(name="postal_code")
	private final String postalCode;
	
	@Column
	private final String country;
	
	@Column
	private final String phone;
	
	@Column
	private final String fax;
	
	@Transient
	private volatile int hashCode;
	
	/*Needed by Hibernate */
	@SuppressWarnings("unused")
	private Customer() {
		this("", "", null, null, null, null, null, null, null, null, null);
	}
	
	public Customer(String customerId,
			String companyNm,
			String contactNm,
			String contactTitle,
			String address,
			String city,
			String region,
			String postalCd,
			String country,
			String phone,
			String fax) {
		Preconditions.checkNotNull(customerId, "customerId cannot be null");
		Preconditions.checkNotNull(companyNm, "companyNm cannot be null");
		this.customerId = customerId;
		this.companyNm = companyNm;
		this.contactNm = contactNm;
		this.contactTitle = contactTitle;
		this.address = address;
		this.city = city;
		this.region = region;
		this.postalCode = postalCd;
		this.country = country;
		this.phone = phone;
		this.fax = fax;
	}
	
	public String getCustomerId() { return customerId; }
	
	public String getCompanyNm() { return companyNm; }
	
	public String getContactNm() { return contactNm; }
	
	public String getContactTitle() { return contactTitle; }
	
	public String getAddress() { return address; }
	
	public String getCity() { return city; }
	
	public String getRegion() { return region; }
	
	public String getPostalCode() { return postalCode; }
	
	public String getCountry() { return country; }
	
	public String getPhone() { return phone; }
	
	public String getFax() { return fax; }
	
	public Customer setCustomerId(String customerId) {
		return new Customer(customerId,
				companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax);
	}
	
	public Customer setCompanyNm(String companyNm) {
		return new Customer(customerId,
				companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax);
	}
	
	public Customer setContactNm(String contactNm) {
		return new Customer(customerId,
				companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax);
	}
	
	public Customer setContactTitle(String contactTitle) {
		return new Customer(customerId,
				companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax);
	}
	
	public Customer setAddress(String address) {
		return new Customer(customerId,
				companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax);
	}
	
	public Customer setCity(String city) {
		return new Customer(customerId,
				companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax);
	}
	
	public Customer setRegion(String region) {
		return new Customer(customerId,
				companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax);
	}
	
	public Customer setPostalCode(String postalCode) {
		return new Customer(customerId,
				companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax);
	}
	
	public Customer setCountry(String country) {
		return new Customer(customerId,
				companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax);
	}
	
	public Customer setPhone(String phone) {
		return new Customer(customerId,
				companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax);
	}
	
	public Customer setFax(String fax) {
		return new Customer(customerId,
				companyNm,
				contactNm,
				contactTitle,
				address,
				city,
				region,
				postalCode,
				country,
				phone,
				fax);
	}
	
	@Override
	public String toString() {
	    return com.google.common.base.Objects.toStringHelper(this)
				.add("customerId", customerId)
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
				.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(!(o instanceof Customer)) return false;
		Customer c = (Customer)o;
		
		return customerId.equalsIgnoreCase(c.customerId) &&
				companyNm.equalsIgnoreCase(c.companyNm) &&
				(contactNm == null ? c.contactNm == null : contactNm.equalsIgnoreCase(c.contactNm)) &&
				(contactTitle == null ? c.contactTitle == null : contactTitle.equalsIgnoreCase(c.contactTitle)) &&
				(address == null ? c.address == null : address.equalsIgnoreCase(c.address)) &&
				(city == null ? c.city == null : city.equalsIgnoreCase(c.city)) &&
				(region == null ? c.region == null : region.equalsIgnoreCase(c.region)) &&
				(postalCode == null ? c.postalCode == null : postalCode.equalsIgnoreCase(c.postalCode)) &&
				(country == null ? c.country == null : country.equalsIgnoreCase(c.country)) &&
				(phone == null ? c.phone == null : phone.equalsIgnoreCase(c.phone)) &&
				(fax == null ? c.fax == null : fax.equalsIgnoreCase(c.fax));
	}
	
	@Override
	public int hashCode() {
		int result = hashCode;
		if(result == 0) {
            // Because in equals, we compare ignoring case, we must convert the Strings to upper case
            // here so that the hash code will be the same if Strings are equal ignoring case.
		    result = Objects.hash(
		            customerId.toUpperCase(),
		            companyNm.toUpperCase(),
		            (contactNm == null ? contactNm : contactNm.toUpperCase()),
		            (contactTitle == null ? contactTitle : contactTitle.toUpperCase()),
		            (address == null ? address : address.toUpperCase()),
		            (city == null ? city : city.toUpperCase()),
		            (region == null ? region : region.toUpperCase()),
		            (postalCode == null ? postalCode : postalCode.toUpperCase()),
		            (country == null ? country : country.toUpperCase()),
		            (phone == null ? phone : phone.toUpperCase()),
		            (fax == null ? fax : fax.toUpperCase())
		            );
		    hashCode = result;
		}
		return result;
	}

	@Override
	public int compareTo(Customer o) {
        // Null is considered less than other values.
        if(o == null) return 1;
        return ComparisonChain.start()
                .compare(customerId, o.customerId, Ordering.from(String.CASE_INSENSITIVE_ORDER))
                .compare(companyNm, o.companyNm, Ordering.from(String.CASE_INSENSITIVE_ORDER))
                .compare(contactNm, o.contactNm, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(contactTitle, o.contactTitle, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(address, o.address, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(city, o.city, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(region, o.region, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(postalCode, o.postalCode, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(country, o.country, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(phone, o.phone, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(fax, o.fax, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .result();
	}

}
