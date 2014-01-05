package co.da.nw.domain;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.google.common.primitives.UnsignedBytes;

@Entity
@Table(name = "employees")
public class Employee implements DomainObject, Comparable<Employee> {

    public static final int MAX_LENGTH_LAST_NM = 20;
    public static final int MAX_LENGTH_FIRST_NM = 10;
    public static final int MAX_LENGTH_TITLE = 30;
    public static final int MAX_LENGTH_TITLE_OF_COURTESY = 25;
    public static final int MAX_LENGTH_ADDRESS = 60;
    public static final int MAX_LENGTH_CITY = 15;
    public static final int MAX_LENGTH_REGION = 15;
    public static final int MAX_LENGTH_POSTAL_CD = 10;
    public static final int MAX_LENGTH_COUNTRY = 15;
    public static final int MAX_LENGTH_HOME_PHONE = 24;
    public static final int MAX_LENGTH_EXTENSION = 4;
    public static final int MAX_LENGTH_NOTES = 1024;
    public static final int MAX_LENGTH_PHOTO_PATH = 255;

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public static class Builder {

        // This reference will save an Employee object that gets passed in the constructor for updating. When build is
        // called, it will check to see if the built object is the same as this one. If it is, it will return this one
        // to avoid publishing a duplicate object.
        private final Employee employee;

        // required
        private final Integer employeeId;
        private final String lastName;
        private final String firstName;

        // optional
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
        private Employee reportsTo;
        private String photoPath;

        public Builder(String lastName, String firstName) {
            this.firstName = firstName;
            this.lastName = lastName;
            employeeId = null;
            employee = null;
        }

        public Builder(Employee employee) {
            this(employee, (employee == null ? null : employee.lastName), employee == null ? null : employee.firstName);
        }

        public Builder(Employee employee, String lastName, String firstName) {
            Preconditions.checkNotNull(employee, "employee must not be null");
            this.employee = employee;
            employeeId = employee.employeeId;
            this.lastName = lastName;
            this.firstName = firstName;

            title = employee.title;
            titleCourtesy = employee.titleCourtesy;
            birthDate = employee.birthDate;
            hireDate = employee.hireDate;
            address = employee.address;
            city = employee.city;
            region = employee.region;
            postalCode = employee.postalCode;
            country = employee.country;
            homePhone = employee.homePhone;
            extension = employee.extension;
            photo = employee.photo;
            notes = employee.notes;
            reportsTo = employee.reportsTo;
            photoPath = employee.photoPath;
        }

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

        public Builder setReportsTo(Employee reportsTo) {
            this.reportsTo = reportsTo;
            return this;
        }

        public Builder setPhotoPath(String photoPath) {
            this.photoPath = photoPath;
            return this;
        }

        public void validate() {
            Preconditions.checkNotNull(lastName, "lastName cannot be null");
            Preconditions.checkArgument(lastName.length() <= MAX_LENGTH_LAST_NM,
                    "lastName must be less than or equal to %s characters", MAX_LENGTH_LAST_NM);

            Preconditions.checkNotNull(firstName, "firstName cannot be null");
            Preconditions.checkArgument(firstName.length() <= MAX_LENGTH_FIRST_NM,
                    "firstName must be less than or equal to %s characters", MAX_LENGTH_FIRST_NM);

            if (title != null) {
                Preconditions.checkArgument(title.length() <= MAX_LENGTH_TITLE,
                        "title must be less than or equal to %s characters", MAX_LENGTH_TITLE);
            }

            if (titleCourtesy != null) {
                Preconditions.checkArgument(titleCourtesy.length() <= MAX_LENGTH_TITLE_OF_COURTESY,
                        "titleCourtesy must be less than or equal to %s characters", MAX_LENGTH_TITLE_OF_COURTESY);
            }

            if (address != null) {
                Preconditions.checkArgument(address.length() <= MAX_LENGTH_ADDRESS,
                        "address must be less than or equal to %s characters", MAX_LENGTH_ADDRESS);
            }

            if (city != null) {
                Preconditions.checkArgument(city.length() <= MAX_LENGTH_CITY,
                        "city must be less than or equal to %s characters", MAX_LENGTH_CITY);
            }

            if (region != null) {
                Preconditions.checkArgument(region.length() <= MAX_LENGTH_REGION,
                        "region must be less than or equal to %s characters", MAX_LENGTH_REGION);
            }

            if (postalCode != null) {
                Preconditions.checkArgument(postalCode.length() <= MAX_LENGTH_POSTAL_CD,
                        "postalCode must be less than or equal to %s characters", MAX_LENGTH_POSTAL_CD);
            }

            if (country != null) {
                Preconditions.checkArgument(country.length() <= MAX_LENGTH_COUNTRY,
                        "country must be less than or equal to %s characters", MAX_LENGTH_COUNTRY);
            }

            if (homePhone != null) {
                Preconditions.checkArgument(homePhone.length() <= MAX_LENGTH_HOME_PHONE,
                        "homePhone must be less than or equal to %s characters", MAX_LENGTH_HOME_PHONE);
            }

            if (extension != null) {
                Preconditions.checkArgument(extension.length() <= MAX_LENGTH_EXTENSION,
                        "extension must be less than or equal to %s characters", MAX_LENGTH_EXTENSION);
            }

            if (notes != null) {
                Preconditions.checkArgument(notes.length() <= MAX_LENGTH_NOTES,
                        "notes must be less than or equal to %s characters", MAX_LENGTH_NOTES);
            }

            if (photoPath != null) {
                Preconditions.checkArgument(photoPath.length() <= MAX_LENGTH_PHOTO_PATH,
                        "photoPath must be less than or equal to %s characters", MAX_LENGTH_PHOTO_PATH);
            }
        }

        public Employee build() {
            validate();
            Employee built = new Employee(this);
            return built.equals(employee) ? employee : built;
        }
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "employee_id")
    private final Integer employeeId;

    @Column(name = "last_name")
    private final String lastName;

    @Column(name = "first_name")
    private final String firstName;

    @Column(name = "title")
    private final String title;

    @Column(name = "title_of_courtesy")
    private final String titleCourtesy;

    @Column(name = "birth_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private final LocalDateTime birthDate;

    @Column(name = "hire_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private final LocalDateTime hireDate;

    @Column(name = "address")
    private final String address;

    @Column(name = "city")
    private final String city;

    @Column(name = "region")
    private final String region;

    @Column(name = "postal_code")
    private final String postalCode;

    @Column(name = "country")
    private final String country;

    @Column(name = "home_phone")
    private final String homePhone;

    @Column(name = "extension")
    private final String extension;

    @Column(name = "photo")
    private final byte[] photo;

    @Column(name = "notes")
    private final String notes;

    @ManyToOne
    @JoinColumn(name = "reports_to")
    private final Employee reportsTo;

    @Column(name = "photo_path")
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

    private Employee(Builder builder) {
        Preconditions.checkNotNull(builder, "builder must not be null");

        employeeId = builder.employeeId;
        lastName = builder.lastName;
        firstName = builder.firstName;
        title = builder.title;
        titleCourtesy = builder.titleCourtesy;
        birthDate = builder.birthDate;
        hireDate = builder.hireDate;
        address = builder.address;
        city = builder.city;
        region = builder.region;
        postalCode = builder.postalCode;
        country = builder.country;
        homePhone = builder.homePhone;
        extension = builder.extension;
        // The Builder makes a copy of the byte array that gets passed to it, so no need to do so here.
        photo = builder.photo;
        notes = builder.notes;
        reportsTo = builder.reportsTo;
        photoPath = builder.photoPath;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleCourtesy() {
        return titleCourtesy;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getExtension() {
        return extension;
    }

    public byte[] getPhoto() {
        return photo == null ? photo : Arrays.copyOf(photo, photo.length);
    }

    public String getNotes() {
        return notes;
    }

    public Employee getReportsTo() {
        return reportsTo;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public Employee setLastName(String lastName) {
        return this.lastName.equals(lastName) ? this : new Employee(new Builder(this, lastName, firstName));
    }

    public Employee setFirstName(String firstName) {
        return this.firstName.equals(firstName) ? this : new Employee(new Builder(this, lastName, firstName));
    }

    public Employee setTitle(String title) {
        return new Employee(new Builder(this).setTitle(title));
    }

    public Employee setTitleCourtesy(String titleCourtesy) {
        return new Employee(new Builder(this).setTitleCourtesy(titleCourtesy));
    }

    public Employee setBirthDate(LocalDateTime birthDate) {
        return new Employee(new Builder(this).setBirthDate(birthDate));
    }

    public Employee setHireDate(LocalDateTime hireDate) {
        return new Employee(new Builder(this).setHireDate(hireDate));
    }

    public Employee setAddress(String address) {
        return new Employee(new Builder(this).setAddress(address));
    }

    public Employee setCity(String city) {
        return new Employee(new Builder(this).setCity(city));
    }

    public Employee setRegion(String region) {
        return new Employee(new Builder(this).setRegion(region));
    }

    public Employee setPostalCode(String postalCode) {
        return new Employee(new Builder(this).setPostalCode(postalCode));
    }

    public Employee setCountry(String country) {
        return new Employee(new Builder(this).setCountry(country));
    }

    public Employee setHomePhone(String homePhone) {
        return new Employee(new Builder(this).setHomePhone(homePhone));
    }

    public Employee setExtension(String extension) {
        return new Employee(new Builder(this).setExtension(extension));
    }

    public Employee setPhoto(byte[] photo) {
        return new Employee(new Builder(this).setPhoto(photo));
    }

    public Employee setNotes(String notes) {
        return new Employee(new Builder(this).setNotes(notes));
    }

    public Employee setReportsTo(Employee reportsTo) {
        return new Employee(new Builder(this).setReportsTo(reportsTo));
    }

    public Employee setPhotoPath(String photoPath) {
        return new Employee(new Builder(this).setPhotoPath(photoPath));
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
        if (o == this)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee e = (Employee) o;

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
        if (result == 0) {
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
        int diff = ComparisonChain.start()
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
        if (diff != 0)
            return diff;

        if (photo == null)
            return o.photo == null ? 0 : -1;
        if (o.photo == null)
            return 1;
        return UnsignedBytes.lexicographicalComparator().compare(photo, o.photo);
    }
}
