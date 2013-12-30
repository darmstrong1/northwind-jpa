package co.da.nw.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonProperty;

import co.da.nw.domain.Customer;

import com.google.common.base.Objects;

public class CustomerDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = Customer.MAX_LENGTH_CUSTOMER_ID, max = Customer.MAX_LENGTH_CUSTOMER_ID,
            message = "Customer ID must be {max} characters.")
    @JsonProperty
    private String customerId;

    @NotNull
    @Size(min = 2, max = Customer.MAX_LENGTH_COMPANY_NM,
            message = "Company Name must be between {min} and {max} characters.")
    @JsonProperty
    private String companyNm;

    @Size(max = Customer.MAX_LENGTH_CONTACT_NM,
            message = "Contact Name must be less than or equal to {max} characters.")
    @JsonProperty
    private String contactNm;

    @Size(max = Customer.MAX_LENGTH_CONTACT_TITLE,
            message = "Contact Title must be less than or equal to {max} characters.")
    @JsonProperty
    private String contactTitle;

    @Size(max = Customer.MAX_LENGTH_ADDRESS, message = "Address must be less than or equal to {max} characters.")
    @JsonProperty
    private String address;

    @Size(max = Customer.MAX_LENGTH_CITY, message = "City must be less than or equal to {max} characters.")
    @JsonProperty
    private String city;

    @Size(max = Customer.MAX_LENGTH_REGION, message = "Region must be less than or equal to {max} characters.")
    @JsonProperty
    private String region;

    @Size(max = Customer.MAX_LENGTH_POSTAL_CD, message = "Postal Code must be less than or equal to {max} characters.")
    @JsonProperty
    private String postalCode;

    @Size(max = Customer.MAX_LENGTH_COUNTRY, message = "Country must be less than or equal to {max} characters.")
    @JsonProperty
    private String country;

    @Size(max = Customer.MAX_LENGTH_PHONE, message = "Phone must be less than or equal to {max} characters.")
    @JsonProperty
    private String phone;

    @Size(max = Customer.MAX_LENGTH_FAX, message = "Fax must be less than or equal to {max} characters.")
    @JsonProperty
    private String fax;

    public CustomerDTO() {

    }

    public CustomerDTO(String customerId,
            String companyNm,
            String contactNm,
            String contactTitle,
            String address,
            String city,
            String region,
            String postalCode,
            String country,
            String phone,
            String fax) {
        
        this.customerId = customerId;
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

    public String getContatcTitle() {
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

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public void setContactNm(String contactNm) {
        this.contactNm = contactNm;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
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
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), customerId, companyNm, contactNm, contactTitle, address, city,
                region, postalCode, country, phone, fax);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof CustomerDTO) {
            if (!super.equals(object))
                return false;
            CustomerDTO that = (CustomerDTO) object;
            return Objects.equal(this.customerId, that.customerId)
                    && Objects.equal(this.companyNm, that.companyNm)
                    && Objects.equal(this.contactNm, that.contactNm)
                    && Objects.equal(this.contactTitle, that.contactTitle)
                    && Objects.equal(this.address, that.address)
                    && Objects.equal(this.city, that.city)
                    && Objects.equal(this.region, that.region)
                    && Objects.equal(this.postalCode, that.postalCode)
                    && Objects.equal(this.country, that.country)
                    && Objects.equal(this.phone, that.phone)
                    && Objects.equal(this.fax, that.fax);
        }
        return false;
    }
}
