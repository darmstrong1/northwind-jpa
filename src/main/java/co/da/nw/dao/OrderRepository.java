package co.da.nw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.da.nw.domain.Customer;
import co.da.nw.domain.Employee;
import co.da.nw.domain.Order;
import co.da.nw.domain.Shipper;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public List<Order> findByCustomer(Customer cust);
    public List<Order> findByEmployee(Employee emp);
    public List<Order> findByShipper(Shipper shipper);

}
