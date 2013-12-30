package co.da.nw.util;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import co.da.nw.domain.Category;
import co.da.nw.domain.Customer;
import co.da.nw.dto.CategoryDTO;
import co.da.nw.dto.CustomerDTO;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

/**
 * This class's purpose is to provide static classes that map one object to another.
 * 
 * @author david
 * 
 */
public class Mapper {

    public static JqgridFilter map(String jsonString) {
        Preconditions.checkNotNull(jsonString, "jsonString must not be null");

        JqgridFilter filter = null;

        ObjectMapper mapper = new ObjectMapper();

        try {
            filter = mapper.readValue(jsonString, JqgridFilter.class);
        } catch (Exception e) {
            // TODO: Create an Exception that extends RuntimeException
            throw new RuntimeException(e);
        }

        return filter;
    }

    @SuppressWarnings("unchecked")
    public static <T, D> D mapDTOToDomain(T dto) {
        D domain = null;

        Class<?> clazz = dto.getClass();

        if (clazz.equals(CategoryDTO.class)) {
            domain = (D) map((CategoryDTO) dto);
        } else if (clazz.equals(CustomerDTO.class)) {
            domain = (D) map((CustomerDTO) dto);
        }

        return domain;
    }

    @SuppressWarnings("unchecked")
    public static <D, T> T mapDomainToDTO(D domain) {
        T dto = null;

        Class<?> clazz = domain.getClass();

        if (clazz.equals(Category.class)) {
            dto = (T) map((Category) domain);
        } else if (clazz.equals(Customer.class)) {
            dto = (T) map((Customer) domain);
        }

        return dto;
    }

    @SuppressWarnings("unchecked")
    public static <T, D> List<D> mapDTOsToDomains(Iterable<T> dtos) {
        Preconditions.checkNotNull(dtos, "dtos must not be null");

        ImmutableList.Builder<D> bldr = new ImmutableList.Builder<>();

        for (T dto : dtos) {
            bldr.add((D) mapDTOToDomain(dto));
        }

        return bldr.build();
    }

    @SuppressWarnings("unchecked")
    public static <D, T> List<T> mapDomainsToDTOs(Iterable<D> domains) {
        Preconditions.checkNotNull(domains, "dtos must not be null");

        ImmutableList.Builder<T> bldr = new ImmutableList.Builder<>();

        for (D domain : domains) {
            bldr.add((T) mapDomainToDTO(domain));
        }

        return bldr.build();
    }

    public static Category map(CategoryDTO dto) {
        Preconditions.checkNotNull(dto, "dto must not be null");

        return new Category.Builder(dto.getName())
                .setDescription(dto.getDescription())
                .build();
    }

    public static CategoryDTO map(Category domain) {
        Preconditions.checkNotNull(domain, "domain must not be null");

        return new CategoryDTO(domain.getCategoryId(),
                domain.getCategoryName(),
                domain.getDescription());
    }

    public static Customer map(CustomerDTO dto) {
        Preconditions.checkNotNull(dto, "dto must not be null");

        return new Customer.Builder(dto.getCustomerId(), dto.getCompanyNm())
                .setContactNm(dto.getContactNm())
                .setAddress(dto.getAddress())
                .setCity(dto.getCity())
                .setContactTitle(dto.getContatcTitle())
                .setCountry(dto.getCountry())
                .setFax(dto.getFax())
                .setPhone(dto.getPhone())
                .setPostalCode(dto.getPostalCode())
                .setRegion(dto.getRegion())
                .build();
    }

    public static CustomerDTO map(Customer domain) {
        Preconditions.checkNotNull(domain, "domain must not be null");

        return new CustomerDTO(domain.getCustomerId(),
                domain.getCompanyNm(),
                domain.getContactNm(),
                domain.getContactTitle(),
                domain.getAddress(),
                domain.getCity(),
                domain.getRegion(),
                domain.getPostalCode(),
                domain.getCountry(),
                domain.getPhone(),
                domain.getFax());
    }

    public static List<Customer> mapToCustomers(Iterable<CustomerDTO> dtos) {
        Preconditions.checkNotNull(dtos, "dtos must not be null");

        ImmutableList.Builder<Customer> bldr = new ImmutableList.Builder<>();

        for (CustomerDTO dto : dtos) {
            bldr.add(map(dto));
        }

        return bldr.build();
    }

    public static List<CustomerDTO> mapToCustomerDTOs(Iterable<Customer> domains) {
        Preconditions.checkNotNull(domains, "domains must not be null");

        ImmutableList.Builder<CustomerDTO> bldr = new ImmutableList.Builder<>();

        for (Customer domain : domains) {
            bldr.add(map(domain));
        }

        return bldr.build();
    }

    public static void main(String[] args) {
        CategoryDTO dto = new CategoryDTO(1L, "name1", "description1");
        Category cat = mapDTOToDomain(dto);

        System.out.println("cat" + cat);

        List<CategoryDTO> dtos = new ArrayList<>();
        dtos.add(dto);
        CategoryDTO dto2 = new CategoryDTO(2L, "name2", "description2");
        dtos.add(dto2);

        List<Category> domains = mapDTOsToDomains(dtos);

        System.out.println("domains" + domains);
    }
}
