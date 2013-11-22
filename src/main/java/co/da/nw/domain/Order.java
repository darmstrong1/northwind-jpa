package co.da.nw.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

@Entity
@Table( name = "orders" )
@Immutable
public final class Order implements DomainObject, Comparable<Order> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static class Builder {
		
		private Customer customer;
		private Employee employee;
		private LocalDateTime orderDate;
		private LocalDateTime requiredDate;
		private LocalDateTime shippedDate;
		private Shipper shipper;
		private BigDecimal freight;
		private String shipName;
		private String shipAddress;
		private String shipCity;
		private String shipRegion;
		private String shipPostalCode;
		private String shipCountry;
		
		public Builder setCustomer(Customer cst) {
			this.customer = cst;
			return this;
		}
		
		public Builder setEmployee(Employee emp) {
			this.employee = emp;
			return this;
		}
		
		public Builder setOrderDate(LocalDateTime date) {
			orderDate = date;
			return this;
		}
		
		public Builder setRequiredDate(LocalDateTime date) {
			requiredDate = date;
			return this;
		}
		
		public Builder setShippedDate(LocalDateTime date) {
			shippedDate = date;
			return this;
		}
		
		public Builder setShipper(Shipper shipper) {
			this.shipper = shipper;
			return this;
		}
		
		public Builder setFreight(BigDecimal freight) {
			this.freight = freight;
			return this;
		}
		
		public Builder setShipName(String name) {
			shipName = name;
			return this;
		}
		
		public Builder setShipAddress(String address) {
			shipAddress = address;
			return this;
		}
		
		public Builder setShipCity(String city) {
			shipCity = city;
			return this;
		}
		
		public Builder setShipRegion(String region) {
			shipRegion = region;
			return this;
		}
		
		public Builder setShipPostalCode(String postalCode) {
			shipPostalCode = postalCode;
			return this;
		}
		
		public Builder setShipCountry(String country) {
			shipCountry = country;
			return this;
		}
		
		public Order build() {
			return new Order(customer,
					employee,
					orderDate,
					requiredDate,
					shippedDate,
					shipper,
					freight,
					shipName,
					shipAddress,
					shipCity,
					shipRegion,
					shipPostalCode,
					shipCountry);
		}
	}
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	@Column(name="order_id")
	private final Long orderId;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private final Customer customer;
	
	@ManyToOne
	@JoinColumn(name="employee_id")
	private final Employee employee;
	
	@Column(name="order_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private final LocalDateTime orderDate;
	
	@Column(name="required_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private final LocalDateTime requiredDate;
	
	@Column(name="shipped_date")
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private final LocalDateTime shippedDate;
	
	@ManyToOne
	@JoinColumn(name="ship_via")
	private final Shipper shipper;
	
	@Column(name="freight")
	private final BigDecimal freight;
	
	@Column(name="ship_name")
	private final String shipName;
	
	@Column(name="ship_address")
	private final String shipAddress;
	
	@Column(name="ship_city")
	private final String shipCity;
	
	@Column(name="ship_region")
	private final String shipRegion;
	
	@Column(name="ship_posta_code")
	private final String shipPostalCode;
	
	@Column(name="ship_country")
	private final String shipCountry;
    
    @Transient
    private volatile int hashCode;
	
	private Order() {
		orderId = null;
		customer = null;
		employee = null;
		orderDate = null;
		requiredDate = null;
		shippedDate = null;
		shipper = null;
		freight = null;
		shipName = null;
		shipAddress = null;
		shipCity = null;
		shipRegion = null;
		shipPostalCode = null;
		shipCountry = null;
	}
	
	private Order(Customer cst,
			Employee emp,
			LocalDateTime orderDate,
			LocalDateTime requiredDate,
			LocalDateTime shippedDate,
			Shipper shipper,
			BigDecimal freight,
			String shipName,
			String shipAddress,
			String shipCity,
			String shipRegion,
			String shipPostalCode,
			String shipCountry) {
		
		orderId = null;
		this.customer = cst;
		this.employee = emp;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.shipper = shipper;
		this.freight = freight;
		this.shipName = shipName;
		this.shipAddress = shipAddress;
		this.shipCity = shipCity;
		this.shipRegion = shipRegion;
		this.shipPostalCode = shipPostalCode;
		this.shipCountry = shipCountry;
	}
	
	public Long getOrderId() { return orderId; }
	public Customer getCustomer() { return customer; }
	public Employee getEmployee() { return employee; }
	public LocalDateTime getOrderDate() { return orderDate; }
	public LocalDateTime getRequiredDate() { return requiredDate; }
	public LocalDateTime getShippedDate() { return shippedDate; }
	public Shipper getShipper() { return shipper; }
	public BigDecimal getFreight() { return freight; }
	public String getShipName() { return shipName; }
	public String getShipAddress() { return shipAddress; }
	public String getShipCity() { return shipCity; }
	public String getRegion() { return shipRegion; }
	public String getPostalCode() { return shipPostalCode; }
	public String getCountry() { return shipCountry; }

	public Order setCustomer(Customer cst) {
		return new Order(cst,
				employee,
				orderDate,
				requiredDate,
				shippedDate,
				shipper,
				freight,
				shipName,
				shipAddress,
				shipCity,
				shipRegion,
				shipPostalCode,
				shipCountry);
	}

	public Order setEmployee(Employee emp) {
		return new Order(customer,
				emp,
				orderDate,
				requiredDate,
				shippedDate,
				shipper,
				freight,
				shipName,
				shipAddress,
				shipCity,
				shipRegion,
				shipPostalCode,
				shipCountry);
	}

	public Order setOrderDate(LocalDateTime orderDate) {
		return new Order(customer,
				employee,
				orderDate,
				requiredDate,
				shippedDate,
				shipper,
				freight,
				shipName,
				shipAddress,
				shipCity,
				shipRegion,
				shipPostalCode,
				shipCountry);
	}

	public Order setRequiredDate(LocalDateTime requiredDate) {
		return new Order(customer,
				employee,
				orderDate,
				requiredDate,
				shippedDate,
				shipper,
				freight,
				shipName,
				shipAddress,
				shipCity,
				shipRegion,
				shipPostalCode,
				shipCountry);
	}

	public Order setShippedDate(LocalDateTime shippedDate) {
		return new Order(customer,
				employee,
				orderDate,
				requiredDate,
				shippedDate,
				shipper,
				freight,
				shipName,
				shipAddress,
				shipCity,
				shipRegion,
				shipPostalCode,
				shipCountry);
	}

	public Order setShipper(Shipper shipper) {
		return new Order(customer,
				employee,
				orderDate,
				requiredDate,
				shippedDate,
				shipper,
				freight,
				shipName,
				shipAddress,
				shipCity,
				shipRegion,
				shipPostalCode,
				shipCountry);
	}

	public Order setFreight(BigDecimal freight) {
		return new Order(customer,
				employee,
				orderDate,
				requiredDate,
				shippedDate,
				shipper,
				freight,
				shipName,
				shipAddress,
				shipCity,
				shipRegion,
				shipPostalCode,
				shipCountry);
	}

	public Order setShipName(String shipName) {
		return new Order(customer,
				employee,
				orderDate,
				requiredDate,
				shippedDate,
				shipper,
				freight,
				shipName,
				shipAddress,
				shipCity,
				shipRegion,
				shipPostalCode,
				shipCountry);
	}

	public Order setShipAddress(String shipAddress) {
		return new Order(customer,
				employee,
				orderDate,
				requiredDate,
				shippedDate,
				shipper,
				freight,
				shipName,
				shipAddress,
				shipCity,
				shipRegion,
				shipPostalCode,
				shipCountry);
	}

	public Order setShipCity(String shipCity) {
		return new Order(customer,
				employee,
				orderDate,
				requiredDate,
				shippedDate,
				shipper,
				freight,
				shipName,
				shipAddress,
				shipCity,
				shipRegion,
				shipPostalCode,
				shipCountry);
	}

	public Order setShipRegion(String shipRegion) {
		return new Order(customer,
				employee,
				orderDate,
				requiredDate,
				shippedDate,
				shipper,
				freight,
				shipName,
				shipAddress,
				shipCity,
				shipRegion,
				shipPostalCode,
				shipCountry);
	}

	public Order setShipPostalCode(String shipPostalCode) {
		return new Order(customer,
				employee,
				orderDate,
				requiredDate,
				shippedDate,
				shipper,
				freight,
				shipName,
				shipAddress,
				shipCity,
				shipRegion,
				shipPostalCode,
				shipCountry);
	}

	public Order setShipCountry(String shipCountry) {
		return new Order(customer,
				employee,
				orderDate,
				requiredDate,
				shippedDate,
				shipper,
				freight,
				shipName,
				shipAddress,
				shipCity,
				shipRegion,
				shipPostalCode,
				shipCountry);
	}
	
	@Override
	public String toString() {
	    return com.google.common.base.Objects.toStringHelper(this)
	            .add("orderId", orderId)
	            .add("customer", customer)
	            .add("employee", employee)
	            .add("orderDate", orderDate)
	            .add("requiredDate", requiredDate)
	            .add("shippedDate", shippedDate)
	            .add("shipper", shipper)
	            .add("freight", freight)
	            .add("shipName", shipName)
	            .add("shipAddress", shipAddress)
	            .add("shipCity", shipCity)
	            .add("shipRegion", shipRegion)
	            .add("shipPostalCode", shipPostalCode)
	            .add("shipCountry", shipCountry)
	            .toString();
	}
	
	@Override
	public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof Order)) return false;
        Order r = (Order)o;
        
        return (customer == null ? r.customer == null : customer.equals(r.customer)) &&
                (employee == null ? r.employee == null : employee.equals(r.employee)) &&
                (orderDate == null ? r.orderDate == null : orderDate.equals(r.orderDate)) &&
                (requiredDate == null ? r.requiredDate == null : requiredDate.equals(r.requiredDate)) &&
                (shippedDate == null ? r.shippedDate == null : shippedDate.equals(r.shippedDate)) &&
                (shipper == null ? r.shipper == null : shipper.equals(r.shipper)) &&
                (freight == null ? r.freight == null : freight.equals(r.freight)) &&
                (shipName == null ? r.shipName == null : shipName.equalsIgnoreCase(r.shipName)) &&
                (shipAddress == null ? r.shipAddress == null : shipAddress.equalsIgnoreCase(r.shipAddress)) &&
                (shipCity == null ? r.shipCity == null : shipCity.equalsIgnoreCase(r.shipCity)) &&
                (shipRegion == null ? r.shipRegion == null : shipRegion.equalsIgnoreCase(r.shipRegion)) &&
                (shipPostalCode == null ? r.shipPostalCode == null : shipPostalCode.equalsIgnoreCase(r.shipPostalCode)) &&
                (shipCountry == null ? r.shipCountry == null : shipCountry.equalsIgnoreCase(r.shipCountry));
	}
    
    @Override
    public int hashCode() {
        int result = hashCode;
        if(result == 0) {
            // Because in equals, we compare ignoring case, we must convert the Strings to upper case
            // here so that the hash code will be the same if Strings are equal ignoring case.
            result = Objects.hash(customer,
                    employee,
                    orderDate,
                    requiredDate,
                    shippedDate,
                    shipper,
                    freight,
                    (shipName == null ? shipName : shipName.toUpperCase()),
                    (shipAddress == null ? shipAddress : shipAddress.toUpperCase()),
                    (shipCity == null ? shipCity : shipCity.toUpperCase()),
                    (shipRegion == null ? shipRegion : shipRegion.toUpperCase()),
                    (shipPostalCode == null ? shipPostalCode : shipPostalCode.toUpperCase()),
                    (shipCountry == null ? shipCountry : shipCountry.toUpperCase()));
            hashCode = result;
        }
        return result;
    }

    @Override
    public int compareTo(Order o) {
        int diff =  ComparisonChain.start()
                .compare(customer, o.customer, Ordering.natural().nullsFirst())
                .compare(employee, o.employee, Ordering.natural().nullsFirst())
                .compare(orderDate, o.orderDate, Ordering.natural().nullsFirst())
                .compare(requiredDate, o.requiredDate, Ordering.natural().nullsFirst())
                .compare(shippedDate, o.shippedDate, Ordering.natural().nullsFirst())
                .compare(shipper, o.shipper, Ordering.natural().nullsFirst())
                .compare(freight, o.freight, Ordering.natural().nullsFirst())
                .compare(shipName, o.shipName, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(shipAddress, o.shipAddress, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(shipCity, o.shipCity, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(shipRegion, o.shipRegion, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(shipPostalCode, o.shipPostalCode, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(shipCountry, o.shipCountry, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .result();
                
        return diff;
    }

}
