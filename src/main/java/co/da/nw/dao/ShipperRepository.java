package co.da.nw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.da.nw.domain.Shipper;

public interface ShipperRepository extends JpaRepository<Shipper, Long> {

	public List<Shipper> findByCompanyName(String name);
}
