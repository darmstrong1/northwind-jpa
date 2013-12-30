package co.da.nw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.da.nw.domain.Customer;
import co.da.nw.dto.CustomerDTO;

import com.mysema.query.types.Predicate;

public interface CustomerService {

    Customer create(CustomerDTO dto);

    Customer delete(String id);

    List<Customer> findAll();

    Page<Customer> findAll(Pageable pageable);

    Page<Customer> findAll(Predicate predicate, Pageable pageable);

    Customer findById(String id);

    Customer update(CustomerDTO dto);
}
