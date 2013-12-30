package co.da.nw.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.da.nw.dao.CustomerRepository;
import co.da.nw.domain.Category;
import co.da.nw.domain.Customer;
import co.da.nw.dto.CustomerDTO;
import co.da.nw.util.Mapper;

import com.mysema.query.types.Predicate;

@Service
public class RepositoryCustomerService implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryCustomerService.class);

    @Resource
    private CustomerRepository repository;

    @Transactional
    @Override
    public Customer create(CustomerDTO dto) {
        LOGGER.debug("Creating a new customer with information: " + dto);

        return repository.save(Mapper.map(dto));
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public Customer delete(String id) {
        LOGGER.debug("Deleting customer with id: " + id);

        Customer deleted = repository.findOne(id);

        if (deleted == null) {
            LOGGER.debug("No customer found with id: " + id);
            throw new EntityNotFoundException(Customer.class.getSimpleName() + " not found with id of " + id);
        }

        repository.delete(deleted);

        return deleted;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Customer> findAll() {
        LOGGER.debug("Finding all customers");
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Customer> findAll(Pageable pageable) {
        LOGGER.debug("Finding all customers");
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Customer> findAll(Predicate predicate, Pageable pageable) {
        LOGGER.debug("Finding all customers that match predicate");
        return repository.findAll(predicate, pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Customer findById(String id) {
        LOGGER.debug("Finding customer by id: " + id);
        return repository.findOne(id);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public Customer update(CustomerDTO dto) {
        LOGGER.debug("Updating customer with information: " + dto);

        Customer existing = repository.findOne(dto.getCustomerId());

        if (existing == null) {
            LOGGER.debug("No customer found with id: " + dto.getCustomerId());
            throw new EntityNotFoundException(Category.class.getSimpleName() + " not found with id of "
                    + dto.getCustomerId());
        }

        Customer.Builder builder = new Customer.Builder(existing, dto.getCustomerId(), dto.getCompanyNm())
                .setAddress(dto.getAddress())
                .setCity(dto.getCity())
                .setContactNm(dto.getContactNm())
                .setContactTitle(dto.getContatcTitle())
                .setCountry(dto.getCountry())
                .setFax(dto.getFax())
                .setPhone(dto.getPhone())
                .setPostalCode(dto.getPostalCode())
                .setRegion(dto.getRegion());

        return repository.save(builder.build());

    }

}
