package co.da.nw.domain;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.collect.ComparisonChain;

@Entity
@Table(name = "order_details")
public class OrderDetail implements DomainObject, Comparable<OrderDetail> {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Id
    private final OrderDetailPk pk;

    @Column(name = "unit_price")
    private final BigDecimal unitPrice;

    @Column(name = "quantity")
    private final Integer quantity;

    @Column(name = "discount")
    private final BigDecimal discount;

    @Transient
    private volatile int hashCode;

    private OrderDetail() {
        pk = null;
        unitPrice = null;
        quantity = null;
        discount = null;
    }

    public OrderDetail(OrderDetailPk pk,
            BigDecimal unitPrice,
            Integer quantity,
            BigDecimal discount) {

        this.pk = pk;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public OrderDetailPk getPk() {
        return pk;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public OrderDetail setPk(OrderDetailPk pk) {
        return new OrderDetail(pk,
                unitPrice,
                quantity,
                discount);
    }

    public OrderDetail setUnitPrice(BigDecimal unitPrice) {
        return new OrderDetail(pk,
                unitPrice,
                quantity,
                discount);
    }

    public OrderDetail setQuantity(Integer quantity) {
        return new OrderDetail(pk,
                unitPrice,
                quantity,
                discount);
    }

    public OrderDetail setDiscount(BigDecimal discount) {
        return new OrderDetail(pk,
                unitPrice,
                quantity,
                discount);
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("pk", pk)
                .add("unitPrice", unitPrice)
                .add("quantity", quantity)
                .add("discount", discount)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderDetail))
            return false;
        OrderDetail od = (OrderDetail) o;

        return pk.equals(od.pk) &&
                unitPrice.equals(od.unitPrice) &&
                quantity.equals(od.quantity) &&
                discount.equals(od.discount);
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            result = Objects.hash(pk, unitPrice, quantity, discount);
            hashCode = result;
        }

        return result;
    }

    @Override
    public int compareTo(OrderDetail o) {
        int diff = ComparisonChain.start()
                .compare(pk, o.pk)
                .compare(unitPrice, o.unitPrice)
                .compare(quantity, o.quantity)
                .compare(discount, o.discount)
                .result();

        return diff;
    }

}
