package co.da.nw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.da.nw.domain.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    public List<Supplier> findByCompanyNm(String companyNm);
}
