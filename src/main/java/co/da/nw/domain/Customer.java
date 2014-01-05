package co.da.nw.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

@Entity
@Table(name = "customers")
public final class Customer implements DomainObject, Comparable<Customer> {

    public static final int MAX_LENGTH_CUSTOMER_ID = 5;
    public static final int MAX_LENGTH_COMPANY_NM = 40;
    public static final int MAX_LENGTH_CONTACT_NM = 30;
    public static final int MAX_LENGTH_CONTACT_TITLE = 30;
    public static final int MAX_LENGTH_ADDRESS = 60;
    public static final int MAX_LENGTH_CITY = 15;
    public static final int MAX_LENGTH_REGION = 15;
    public static final int MAX_LENGTH_POSTAL_CD = 10;
    public static final int MAX_LENGTH_COUNTRY = 15;
    public static final int MAX_LENGTH_PHONE = 24;
    public static final int MAX_LENGTH_FAX = 24;

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public static class Builder {

        // This reference will save a Customer object that gets passed in the constructor for updating. When build is
        // called, it will check to see if the built object is the same as this one. If it is, it will return this one
        // to avoid publishing a duplicate object.
        private final Customer customer;

        // Required fields
        private final String customerId;
        private final String companyNm;

        // Optional fields
        private String contactNm;
        private String contactTitle;
        private String address;
        private String city;
        private String region;
        private String postalCode;
        private String country;
        private String phone;
        private String fax;

        public Builder(String customerId, String companyNm) {
            this.customerId = customerId;
            this.companyNm = companyNm;
            customer = null;
        }

        /**
         * This constructor is intended to build an update an entity that has already been saved in the database, but it
         * can also be used to build an update of an entity that has not been saved. This buildUpdate method should be
         * called if you want to update multiple elements excluding the customerId and companyNm. existing must not be
         * null.
         * 
         * @param customer
         *            The Customer object to update. Any updates should be set by calling the appropriate set method.
         */
        public Builder(Customer customer) {
            this(customer, (customer == null ? null : customer.customerId), customer == null ? null
                    : customer.companyNm);
        }

        /**
         * This constructor is intended to build an update an entity that has already been saved in the database, but it
         * can also be used to build an update of an entity that has not been saved. This buildUpdate method should be
         * called if you want to update multiple elements including the customerId and/or companyNm. All arguments must
         * not be null.
         * 
         * @param customer
         *            The Customer object to update. Any updates should be set by calling the appropriate set method.
         * @param customerId
         * @param companyNm
         */
        public Builder(Customer customer, String customerId, String companyNm) {
            Preconditions.checkNotNull(customer, "customer must not be null");

            this.customer = customer;
            this.customerId = customerId;
            this.companyNm = companyNm;

            contactNm = customer.contactNm;
            contactTitle = customer.contactTitle;
            address = customer.address;
            city = customer.city;
            region = customer.region;
            postalCode = customer.postalCode;
            country = customer.country;
            phone = customer.phone;
            fax = customer.fax;
        }

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

        private void validate() {
            Preconditions.checkNotNull(customerId, "customerId cannot be null");
            Preconditions.checkArgument(customerId.length() == MAX_LENGTH_CUSTOMER_ID,
                    "customerId must be equal to %s characters", MAX_LENGTH_CUSTOMER_ID);

            Preconditions.checkNotNull(companyNm, "companyNm cannot be null");
            Preconditions.checkArgument(companyNm.length() <= MAX_LENGTH_COMPANY_NM,
                    "customerId must be less than or equal to %s characters", MAX_LENGTH_COMPANY_NM);

            if (contactNm != null) {
                Preconditions.checkArgument(contactNm.length() <= MAX_LENGTH_CONTACT_NM,
                        "contactNm must be less than or equal to %s characters", MAX_LENGTH_CONTACT_NM);
            }

            if (contactTitle != null) {
                Preconditions.checkArgument(contactTitle.length() <= MAX_LENGTH_CONTACT_TITLE,
                        "contactTitle must be less than or equal to %s characters", MAX_LENGTH_CONTACT_TITLE);
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
                        "postalCd must be less than or equal to %s characters", MAX_LENGTH_POSTAL_CD);
            }

            if (country != null) {
                Preconditions.checkArgument(country.length() <= MAX_LENGTH_COUNTRY,
                        "country must be less than or equal to %s characters", MAX_LENGTH_COUNTRY);
            }

            if (phone != null) {
                Preconditions.checkArgument(phone.length() <= MAX_LENGTH_PHONE,
                        "phone must be less than or equal to %s characters", MAX_LENGTH_PHONE);
            }

            if (fax != null) {
                Preconditions.checkArgument(fax.length() <= MAX_LENGTH_FAX,
                        "fax must be less than or equal to %s characters", MAX_LENGTH_FAX);
            }
        }

        public Customer build() {
            validate();
            Customer built = new Customer(this);
            return built.equals(customer) ? customer : built;
        }
    }

    @Id
    @Column(name = "customer_id", columnDefinition = "bpchar")
    private final String customerId;

    @Column(name = "company_name")
    private final String companyNm;

    @Column(name = "contact_name")
    private final String contactNm;

    @Column(name = "contact_title")
    private final String contactTitle;

    @Column
    private final String address;

    @Column
    private final String city;

    @Column
    private final String region;

    @Column(name = "postal_code")
    private final String postalCode;

    @Column
    private final String country;

    @Column
    private final String phone;

    @Column
    private final String fax;

    @Transient
    private volatile int hashCode;

    /* Needed by Hibernate */
    @SuppressWarnings("unused")
    private Customer() {
        this(new Builder("", ""));
    }

    private Customer(Builder builder) {
        Preconditions.checkNotNull(builder, "builder must not be null");

        customerId = builder.customerId;
        companyNm = builder.companyNm;
        contactNm = builder.contactNm;
        contactTitle = builder.contactTitle;
        address = builder.address;
        city = builder.city;
        region = builder.region;
        postalCode = builder.postalCode;
        country = builder.country;
        phone = builder.phone;
        fax = builder.fax;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public String getContactNm() {
        return contactNm;
    }

    public String getContactTitle() {
        return contactTitle;
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

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public Customer setCustomerId(String customerId) {
        return this.customerId.equals(customerId) ? this : new Customer(new Builder(this, customerId, companyNm));
    }

    public Customer setCompanyNm(String companyNm) {
        return this.companyNm.equals(companyNm) ? this : new Customer(new Builder(this, customerId, companyNm));
    }

    public Customer setContactNm(String contactNm) {
        return new Customer(new Builder(this).setContactNm(contactNm));
    }

    public Customer setContactTitle(String contactTitle) {
        return new Customer(new Builder(this).setContactTitle(contactTitle));
    }

    public Customer setAddress(String address) {
        return new Customer(new Builder(this).setAddress(address));
    }

    public Customer setCity(String city) {
        return new Customer(new Builder(this).setCity(city));
    }

    public Customer setRegion(String region) {
        return new Customer(new Builder(this).setRegion(region));
    }

    public Customer setPostalCode(String postalCode) {
        return new Customer(new Builder(this).setPostalCode(postalCode));
    }

    public Customer setCountry(String country) {
        return new Customer(new Builder(this).setCountry(country));
    }

    public Customer setPhone(String phone) {
        return new Customer(new Builder(this).setPhone(phone));
    }

    public Customer setFax(String fax) {
        return new Customer(new Builder(this).setFax(fax));
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
        if (o == this)
            return true;
        if (!(o instanceof Customer))
            return false;
        Customer c = (Customer) o;

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
        if (result == 0) {
            // Because in equals, we compare ignoring case, we must convert the
            // Strings to upper case
            // here so that the hash code will be the same if Strings are equal
            // ignoring case.
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
        if (o == null)
            return 1;
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
