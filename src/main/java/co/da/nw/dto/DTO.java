package co.da.nw.dto;

public interface DTO<T> {

    T getDomainObject();
    void loadFromDomainObject(T domainObject);
}
