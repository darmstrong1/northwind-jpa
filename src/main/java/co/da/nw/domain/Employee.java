package co.da.nw.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;

import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.google.common.primitives.UnsignedBytes;

import org.joda.time.LocalDateTime;

@Entity
@Table( name = "employees" )
@Immutable
public class Employee implements DomainObject, Comparable<Employee> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static class Builder {
		
		  private String title;
		  private String titleCourtesy;
		  private LocalDateTime birthDate;
		  private LocalDateTime hireDate;
		  private String address;
		  private String city;
		  private String region;
		  private String postalCode;
		  private String country;
		  private String homePhone;
		  private String extension;
		  private byte[] photo;
		  private String notes;
		  private Long reportsTo;
		  private String photoPath;
		  
		  public Builder setTitle(String title) {
			  this.title = title;
			  return this;
		  }
		  
		  public Builder setTitleCourtesy(String titleCourtesy) {
			  this.titleCourtesy = titleCourtesy;
			  return this;
		  }
		  
		  public Builder setBirthDate(LocalDateTime birthDate) {
			  this.birthDate = birthDate;
			  return this;
		  }
		  
		  public Builder setHireDate(LocalDateTime hireDate) {
			  this.hireDate = hireDate;
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
		  
		  public Builder setHomePhone(String homePhone) {
			  this.homePhone = homePhone;
			  return this;
		  }
		  
		  public Builder setExtension(String extension) {
			  this.extension = extension;
			  return this;
		  }
		  
		  public Builder setPhoto(byte[] photo) {
			  this.photo = Arrays.copyOf(photo, photo.length);
			  return this;
		  }
		  
		  public Builder setNotes(String notes) {
			  this.notes = notes;
			  return this;
		  }
		  
		  public Builder setReportsTo(Long reportsTo) {
			  this.reportsTo = reportsTo;
			  return this;
		  }
		  
		  public Builder setPhotoPath(String photoPath) {
			  this.photoPath = photoPath;
			  return this;
		  }
		  
		  public Employee build(String lastName, String firstName) {
			  return new Employee(lastName,
					  firstName,
					  title,
					  titleCourtesy,
					  birthDate,
					  hireDate,
					  address,
					  city,
					  region,
					  postalCode,
					  country,
					  homePhone,
					  extension,
					  photo,
					  notes,
					  reportsTo,
					  photoPath);
		  }
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="employee_id")
	private final Integer employeeId;
	
	@Column(name="last_name")
	private final String lastName;
	
	
	@Column(name="first_name")
	private final String firstName;
	
	@Column(name="title")
	private final String title;
	
	@Column(name="title_of_courtesy")
	private final String titleCourtesy;
	
	@Column(name="birth_date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private final LocalDateTime birthDate;
	
	@Column(name="hire_date")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private final LocalDateTime hireDate;
	
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
	
	@Column(name="home_phone")
	private final String homePhone;
	
	@Column(name="extension")
	private final String extension;
	
	@Column(name="photo")
	private final byte[] photo;
	
	@Column(name="notes")
	private final String notes;
	
	@Column(name="reports_to")
	private final Long reportsTo;
	
	@Column(name="photo_path")
	private final String photoPath;
	
	@Transient
	private volatile int hashCode;
	
	private Employee() {
		employeeId = null;
	    lastName = null;
		firstName = null;
		title = null;
		titleCourtesy = null;
		birthDate = null;
		hireDate = null;
		address = null;
		city = null;
		region = null;
		postalCode = null;
		country = null;
		homePhone = null;
		extension = null;
		photo = null;
		notes = null;
		reportsTo = null;
		photoPath = null;
	}
	
	private Employee(String lastName,
			String firstName,
			String title,
			String titleCourtesy,
			LocalDateTime birthDate,
			LocalDateTime hireDate,
			String address,
			String city,
			String region,
			String postalCode,
			String country,
			String homePhone,
			String extension,
			byte[] photo,
			String notes,
			Long reportsTo,
			String photoPath) {
		Preconditions.checkNotNull(lastName, "lastName cannot be null");
		Preconditions.checkNotNull(firstName, "firstName cannot be null");
		this.employeeId = null;
		this.lastName = lastName;
		this.firstName = firstName;
		this.title = title;
		this.titleCourtesy = titleCourtesy;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
		this.address = address;
		this.city = city;
		this.region = region;
		this.postalCode = postalCode;
		this.country = country;
		this.homePhone = homePhone;
		this.extension = extension;
	    // The Builder makes a copy of the byte array that gets passed to it, so no need to do so here.
		this.photo = photo;
		this.notes = notes;
		this.reportsTo = reportsTo;
		this.photoPath = photoPath;
	}
	
	public Integer getEmployeeId() { return employeeId; }
	public String getLastName() { return lastName; }
	public String getFirstName() { return firstName; }
	public String getTitle() { return title; }
	public String getTitleCourtesy() { return titleCourtesy; }
	public LocalDateTime getBirthDate() { return birthDate; }
	public LocalDateTime getHireDate() { return hireDate; }
	public String getAddress() { return address; }
	public String getCity() { return city; }
	public String getRegion() { return region; }
	public String getPostalCode() { return postalCode; }
	public String getCountry() { return country; }
	public String getHomePhone() { return homePhone; }
	public String getExtension() { return extension; }
	public byte[] getPhoto() { return photo == null ? photo : Arrays.copyOf(photo, photo.length); }
	public String getNotes() { return notes; }
	public Long getReportsTo() { return reportsTo; }
	public String getPhotoPath() { return photoPath; }
	
	public Employee setLastName(String lastName) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setFirstName(String firstName) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setTitle(String title) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setTitleCourtesy(String titleCourtesy) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setBirthDate(LocalDateTime birthDate) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setHireDate(LocalDateTime hireDate) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setAddress(String address) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setCity(String city) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setRegion(String region) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setPostalCode(String postalCode) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setCountry(String country) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setHomePhone(String homePhone) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setExtension(String extension) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setPhoto(byte[] photo) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo == null ? photo : Arrays.copyOf(photo, photo.length),
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setNotes(String notes) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setReportsTo(Long reportsTo) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	public Employee setPhotoPath(String photoPath) {
		return new Employee(lastName,
				firstName,
				title,
				titleCourtesy,
				birthDate,
				hireDate,
				address,
				city,
				region,
				postalCode,
				country,
				homePhone,
				extension,
				photo,
				notes,
				reportsTo,
				photoPath);
	}
	
	@Override
	public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("employeeId", employeeId)
                .add("lastName", lastName)
                .add("firstName", firstName)
                .add("title", title)
                .add("titleCourtesy", titleCourtesy)
                .add("birthDate", birthDate)
                .add("hireDate", hireDate)
                .add("address", address)
                .add("city", city)
                .add("region", region)
                .add("postalCode", postalCode)
                .add("country", country)
                .add("homePhone", homePhone)
                .add("extension", extension)
                .add("photo", photo)
                .add("notes", notes)
                .add("reportsTo", reportsTo)
                .add("photoPath", photoPath)
                .toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) return true;
		if(!(o instanceof Employee)) return false;
		Employee e = (Employee)o;

        return lastName.equalsIgnoreCase(e.lastName) &&
                firstName.equalsIgnoreCase(e.firstName) &&
                (title == null ? e.title == null : title.equalsIgnoreCase(e.title)) &&
                (titleCourtesy == null ? e.titleCourtesy == null : titleCourtesy.equalsIgnoreCase(e.titleCourtesy)) &&
                (birthDate == null ? e.birthDate == null : birthDate.equals(e.birthDate)) &&
                (hireDate == null ? e.hireDate == null : hireDate.equals(e.hireDate)) &&
                (address == null ? e.address == null : address.equalsIgnoreCase(e.address)) &&
                (city == null ? e.city == null : city.equalsIgnoreCase(e.city)) &&
                (region == null ? e.region == null : region.equalsIgnoreCase(e.region)) &&
                (postalCode == null ? e.postalCode == null : postalCode.equalsIgnoreCase(e.postalCode)) &&
                (country == null ? e.country == null : country.equalsIgnoreCase(e.country)) &&
                (homePhone == null ? e.homePhone == null : homePhone.equalsIgnoreCase(e.homePhone)) &&
                (extension == null ? e.extension == null : extension.equalsIgnoreCase(e.extension)) &&
                Arrays.equals(photo, e.photo) &&
                (notes == null ? e.notes == null : notes.equalsIgnoreCase(e.notes)) &&
                (reportsTo == null ? e.reportsTo == null : reportsTo.equals(e.reportsTo)) &&
                (photoPath == null ? e.photoPath == null : photoPath.equalsIgnoreCase(e.photoPath));
	}
	
	@Override
	public int hashCode() {
        int result = hashCode;
        if(result == 0) {
            // Because in equals, we compare ignoring case, we must convert the Strings to upper case
            // here so that the hash code will be the same if Strings are equal ignoring case.
            result = Objects.hash(
                    lastName.toUpperCase(),
                    firstName.toUpperCase(),
                    (title == null ? title : title.toUpperCase()),
                    (titleCourtesy == null ? titleCourtesy : titleCourtesy.toUpperCase()),
                    birthDate,
                    hireDate,
                    (address == null ? address : address.toUpperCase()),
                    (city == null ? city : city.toUpperCase()),
                    (region == null ? region : region.toUpperCase()),
                    (postalCode == null ? postalCode : postalCode.toUpperCase()),
                    (country == null ? country : country.toUpperCase()),
                    (homePhone == null ? homePhone : homePhone.toUpperCase()),
                    (extension == null ? extension : extension.toUpperCase()),
                    Arrays.hashCode(photo),
                    reportsTo,
                    (photoPath == null ? photoPath : photoPath.toUpperCase())
                    );
            hashCode = result;
        }
        return result;
	}

	@Override
	public int compareTo(Employee o) {
		int diff =  ComparisonChain.start()
				.compare(lastName, o.lastName, Ordering.from(String.CASE_INSENSITIVE_ORDER))
				.compare(firstName, o.firstName, Ordering.from(String.CASE_INSENSITIVE_ORDER))
				.compare(title, o.title, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
				.compare(titleCourtesy, o.titleCourtesy, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
				.compare(birthDate, o.birthDate, Ordering.natural().nullsFirst())
				.compare(hireDate, o.hireDate, Ordering.natural().nullsFirst())
				.compare(address, o.address, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
				.compare(city, o.city, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
				.compare(region, o.region, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
				.compare(postalCode, o.postalCode, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
				.compare(country, o.country, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
				.compare(homePhone, o.homePhone, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
				.compare(extension, o.extension, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
				.compare(reportsTo, o.reportsTo, Ordering.natural().nullsFirst())
				.result();
		if(diff != 0) return diff;
		
		if(photo == null) return o.photo == null ? 0 : -1;
		if(o.photo == null) return 1;
		return UnsignedBytes.lexicographicalComparator().compare(photo, o.photo);		
	}
}
