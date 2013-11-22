package co.da.nw.domain;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;

@Embeddable
public class OrderDetailPk implements DomainObject, Comparable<OrderDetailPk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private final Order order;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private final Product product;
    
    @Transient
    private volatile int hashCode;
	
	private OrderDetailPk() {
	    this(new Order.Builder().build(), new Product.Builder().build("", 0));
	}
	
	public OrderDetailPk(Order order, Product product) {
	    Preconditions.checkNotNull(order, "order must not be null.");
	    Preconditions.checkNotNull(product, "product must not be null.");
		this.order = order;
		this.product = product;
	}
	
	public Order getOrder() { return order; }
	public Product getProduct() { return product; }
	
	public OrderDetailPk setOrder(Order order) { return new OrderDetailPk(order, product); }
	public OrderDetailPk setProduct(Product product) { return new OrderDetailPk(order, product); }
	
	@Override
	public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("order", order)
                .add("product", product)
                .toString();
	}
	
	@Override
	public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof OrderDetailPk)) return false;
        OrderDetailPk pk = (OrderDetailPk)o;
        
        return order.equals(pk.order) &&
                product.equals(pk.product);
	}
	
	@Override
	public int hashCode() {
        int result = hashCode;
        if(result == 0) {
            result = Objects.hash(order, product);
            hashCode = result;
        }
        
        return result;
	}

    @Override
    public int compareTo(OrderDetailPk o) {
        int diff = ComparisonChain.start()
                .compare(order, o.order)
                .compare(product, o.product)
                .result();
        
        return diff;
    }
}
