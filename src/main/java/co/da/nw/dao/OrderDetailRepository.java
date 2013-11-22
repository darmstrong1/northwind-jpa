package co.da.nw.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import co.da.nw.domain.OrderDetail;
import co.da.nw.domain.OrderDetailPk;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailPk>,
        JpaSpecificationExecutor<OrderDetail> {

}
