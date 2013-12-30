package co.da.nw.dao;

import co.da.nw.domain.QCustomer;

import com.mysema.query.types.Predicate;

public class CustomerPredicates {

    public static Predicate customerIdIs(String customerId) {
        return QCustomer.customer.customerId.equalsIgnoreCase(customerId);
    }

    public static Predicate companyNmIs(String companyNm) {
        return QCustomer.customer.companyNm.equalsIgnoreCase(companyNm);
    }

    public static Predicate contactNmIs(String contactNm) {
        return QCustomer.customer.contactNm.equalsIgnoreCase(contactNm);
    }

    public static Predicate contactTitleIs(String contactTitle) {
        return QCustomer.customer.contactTitle.equalsIgnoreCase(contactTitle);
    }

    public static Predicate addressis(String address) {
        return QCustomer.customer.address.equalsIgnoreCase(address);
    }

    public static Predicate cityIs(String city) {
        return QCustomer.customer.city.equalsIgnoreCase(city);
    }

    public static Predicate regionIs(String region) {
        return QCustomer.customer.region.equalsIgnoreCase(region);
    }

    public static Predicate postalCdIs(String postalCd) {
        return QCustomer.customer.postalCode.equalsIgnoreCase(postalCd);
    }

    public static Predicate phoneIs(String phone) {
        return QCustomer.customer.phone.equalsIgnoreCase(phone);
    }

    public static Predicate faxIs(String fax) {
        return QCustomer.customer.fax.equalsIgnoreCase(fax);
    }

    public static Predicate countryIs(String country) {
        return QCustomer.customer.country.equalsIgnoreCase(country);
    }

    public static Predicate customerIdIsLike(String customerId) {
        return QCustomer.customer.customerId.startsWithIgnoreCase(customerId);
    }

    public static Predicate companyNmIsLike(String companyNm) {
        return QCustomer.customer.companyNm.startsWithIgnoreCase(companyNm);
    }

    public static Predicate contactNmIsLike(String contactNm) {
        return QCustomer.customer.contactNm.startsWithIgnoreCase(contactNm);
    }

    public static Predicate contactTitleIsLike(String contactTitle) {
        return QCustomer.customer.contactTitle.startsWithIgnoreCase(contactTitle);
    }

    public static Predicate addressIsLike(String address) {
        return QCustomer.customer.address.startsWithIgnoreCase(address);
    }

    public static Predicate cityIsLike(String city) {
        return QCustomer.customer.city.startsWithIgnoreCase(city);
    }

    public static Predicate regionIsLike(String region) {
        return QCustomer.customer.region.startsWithIgnoreCase(region);
    }

    public static Predicate postalCdIsLike(String postalCd) {
        return QCustomer.customer.postalCode.startsWithIgnoreCase(postalCd);
    }

    public static Predicate phoneIsLike(String phone) {
        return QCustomer.customer.phone.startsWithIgnoreCase(phone);
    }

    public static Predicate faxIsLike(String fax) {
        return QCustomer.customer.fax.startsWithIgnoreCase(fax);
    }

    public static Predicate countryIsLike(String country) {
        return QCustomer.customer.country.startsWithIgnoreCase(country);
    }

}
