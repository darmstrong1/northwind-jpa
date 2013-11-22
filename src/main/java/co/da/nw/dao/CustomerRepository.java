package co.da.nw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.da.nw.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	public List<Customer> findByCompanyNm(String companyNm);

}
