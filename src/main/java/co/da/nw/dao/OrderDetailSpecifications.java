package co.da.nw.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import co.da.nw.domain.Order;
import co.da.nw.domain.OrderDetail;
import co.da.nw.domain.OrderDetailPk;
import co.da.nw.domain.OrderDetailPk_;
import co.da.nw.domain.OrderDetail_;
import co.da.nw.domain.Product;

public class OrderDetailSpecifications {

    public static Specification<OrderDetail> orderIs(final Order order) {
        return new Specification<OrderDetail>() {

            @Override
            public Predicate toPredicate(Root<OrderDetail> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<OrderDetailPk> pkPath = root.<OrderDetailPk>get(OrderDetail_.pk);
                Path<Order> orderPath = pkPath.get(OrderDetailPk_.order);
                return cb.equal(orderPath, order);
            }
        };
    }

    public static Specification<OrderDetail> productIs(final Product product) {
        return new Specification<OrderDetail>() {

            @Override
            public Predicate toPredicate(Root<OrderDetail> root,
                    CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<OrderDetailPk> pkPath = root.<OrderDetailPk>get(OrderDetail_.pk);
                Path<Product> productPath = pkPath.get(OrderDetailPk_.product);
                return cb.equal(productPath, product);
            }
        };
    }
}
