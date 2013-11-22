package co.da.nw.domain;

import java.math.BigDecimal;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderDetail.class)
public abstract class OrderDetail_ {
    public static volatile SingularAttribute<OrderDetail, OrderDetailPk> pk;
    public static volatile SingularAttribute<OrderDetail, BigDecimal> unitPrice;
    public static volatile SingularAttribute<OrderDetail, Integer> quantity;
    public static volatile SingularAttribute<OrderDetail, BigDecimal> discount;
}
