package co.da.nw.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrderDetailPk.class)
public abstract class OrderDetailPk_ {
    public static volatile SingularAttribute<OrderDetailPk, Order> order;
    public static volatile SingularAttribute<OrderDetailPk, Product> product;
}
