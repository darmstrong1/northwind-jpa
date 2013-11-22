package co.da.nw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.da.nw.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByProductNm(String productNm);
}
