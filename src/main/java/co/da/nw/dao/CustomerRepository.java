package co.da.nw.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import co.da.nw.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>, QueryDslPredicateExecutor<Customer> {
    public List<Customer> findByCompanyNm(String companyNm);

    public Page<Customer> findByCompanyNmLike(String companyNm, Pageable pageable);

    public Page<Customer> findByContactNmLike(String contactNm, Pageable pageable);

}
