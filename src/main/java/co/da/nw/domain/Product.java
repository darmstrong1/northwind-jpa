package co.da.nw.domain;

import java.math.BigDecimal;
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

import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

@Entity
@Table(name = "products")
public class Product implements DomainObject, Comparable<Product> {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public static class Builder {

        private Supplier supplier;
        private Category category;
        private String quantityPerUnit;
        private BigDecimal unitPrice;
        private Integer unitsInStock;
        private Integer unitsonOrder;
        private Integer reorderLevel;

        public Builder setSupplier(Supplier supplier) {
            this.supplier = supplier;
            return this;
        }

        public Builder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder setQuantityPerUnit(String quantityPerUnit) {
            this.quantityPerUnit = quantityPerUnit;
            return this;
        }

        public Builder setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder setUnitsInStock(Integer unitsInStock) {
            this.unitsInStock = unitsInStock;
            return this;
        }

        public Builder setUnitsOnOrder(Integer unitsOnOrder) {
            this.unitsonOrder = unitsOnOrder;
            return this;
        }

        public Builder setReorderLevel(Integer reorderLevel) {
            this.reorderLevel = reorderLevel;
            return this;
        }

        public Product build(String productNm, Integer discontinued) {
            return new Product(productNm,
                    supplier,
                    category,
                    quantityPerUnit,
                    unitPrice,
                    unitsInStock,
                    unitsonOrder,
                    reorderLevel,
                    discontinued);
        }
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "product_id")
    private final Long productId;

    @Column(name = "product_name")
    private final String productNm;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private final Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private final Category category;

    @Column(name = "quantity_per_unit")
    private final String quantityPerUnit;

    @Column(name = "unit_price")
    private final BigDecimal unitPrice;

    @Column(name = "units_in_stock")
    private final Integer unitsInStock;

    @Column(name = "units_on_order")
    private final Integer unitsOnOrder;

    @Column(name = "reorder_level")
    private final Integer reorderLevel;

    @Column(name = "discontinued")
    private final Integer discontinued;

    @Transient
    private volatile int hashCode;

    private Product() {

        this("",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                0);
    }

    private Product(String productNm,
            Supplier supplier,
            Category category,
            String quantityPerUnit,
            BigDecimal unitPrice,
            Integer unitsInStock,
            Integer unitsOnOrder,
            Integer reorderLevel,
            Integer discontinued) {

        Preconditions.checkNotNull(productNm, "productNm cannot be null.");
        Preconditions.checkNotNull(discontinued, "discontinued cannot be null.");
        productId = null;
        this.productNm = productNm;
        this.supplier = supplier;
        this.category = category;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reorderLevel = reorderLevel;
        this.discontinued = discontinued;

    }

    public Long getProductId() {
        return productId;
    }

    public String getProductNm() {
        return productNm;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Category getCategory() {
        return category;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    public Integer getUnitsInOrder() {
        return unitsOnOrder;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public Integer getDiscontinued() {
        return discontinued;
    }

    public Product setProductNm(String productNm) {
        return new Product(productNm,
                supplier,
                category,
                quantityPerUnit,
                unitPrice,
                unitsInStock,
                unitsOnOrder,
                reorderLevel,
                discontinued);
    }

    public Product setSupplier(Supplier supplier) {
        return new Product(productNm,
                supplier,
                category,
                quantityPerUnit,
                unitPrice,
                unitsInStock,
                unitsOnOrder,
                reorderLevel,
                discontinued);
    }

    public Product setCategory(Category category) {
        return new Product(productNm,
                supplier,
                category,
                quantityPerUnit,
                unitPrice,
                unitsInStock,
                unitsOnOrder,
                reorderLevel,
                discontinued);
    }

    public Product setQuantityPerUnit(String quantityPerUnit) {
        return new Product(productNm,
                supplier,
                category,
                quantityPerUnit,
                unitPrice,
                unitsInStock,
                unitsOnOrder,
                reorderLevel,
                discontinued);
    }

    public Product setUnitPrice(BigDecimal unitPrice) {
        return new Product(productNm,
                supplier,
                category,
                quantityPerUnit,
                unitPrice,
                unitsInStock,
                unitsOnOrder,
                reorderLevel,
                discontinued);
    }

    public Product setUnitsInStock(Integer unitsInStock) {
        return new Product(productNm,
                supplier,
                category,
                quantityPerUnit,
                unitPrice,
                unitsInStock,
                unitsOnOrder,
                reorderLevel,
                discontinued);
    }

    public Product setUnitsOnOrder(Integer unitsOnOrder) {
        return new Product(productNm,
                supplier,
                category,
                quantityPerUnit,
                unitPrice,
                unitsInStock,
                unitsOnOrder,
                reorderLevel,
                discontinued);
    }

    public Product setReorderLevel(Integer reorderLevel) {
        return new Product(productNm,
                supplier,
                category,
                quantityPerUnit,
                unitPrice,
                unitsInStock,
                unitsOnOrder,
                reorderLevel,
                discontinued);
    }

    public Product setDiscontinued(Integer discontinued) {
        return new Product(productNm,
                supplier,
                category,
                quantityPerUnit,
                unitPrice,
                unitsInStock,
                unitsOnOrder,
                reorderLevel,
                discontinued);
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("productId", productId)
                .add("productNm", productNm)
                .add("supplier", supplier)
                .add("category", category)
                .add("quantityPerUnit", quantityPerUnit)
                .add("unitPrice", unitPrice)
                .add("unitsInStock", unitsInStock)
                .add("unitsOnOrder", unitsOnOrder)
                .add("reorderLevel", reorderLevel)
                .add("discontinued", discontinued)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product))
            return false;
        Product p = (Product) o;

        return productNm.equalsIgnoreCase(p.productNm)
                &&
                (supplier == null ? p.supplier == null : supplier.equals(p.supplier))
                &&
                (category == null ? p.category == null : category.equals(p.category))
                &&
                (quantityPerUnit == null ? p.quantityPerUnit == null : quantityPerUnit
                        .equalsIgnoreCase(p.quantityPerUnit)) &&
                (unitPrice == null ? p.unitPrice == null : unitPrice.equals(p.unitPrice)) &&
                (unitsInStock == null ? p.unitsInStock == null : unitsInStock.equals(p.unitsInStock)) &&
                (unitsOnOrder == null ? p.unitsOnOrder == null : unitsOnOrder.equals(p.unitsOnOrder)) &&
                (reorderLevel == null ? p.reorderLevel == null : reorderLevel.equals(p.reorderLevel)) &&
                discontinued.equals(p.discontinued);
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            // Because in equals, we compare ignoring case, we must convert the Strings to upper case
            // here so that the hash code will be the same if Strings are equal ignoring case.
            result = Objects.hash(productNm.toUpperCase(),
                    supplier,
                    category,
                    quantityPerUnit,
                    unitPrice,
                    unitsInStock,
                    unitsOnOrder,
                    reorderLevel,
                    discontinued);

            hashCode = result;
        }
        return result;
    }

    @Override
    public int compareTo(Product o) {
        // Null is considered less than other values.
        if (o == null)
            return 1;
        int diff = ComparisonChain.start()
                .compare(productNm, o.productNm, Ordering.from(String.CASE_INSENSITIVE_ORDER))
                .compare(supplier, o.supplier, Ordering.natural().nullsFirst())
                .compare(category, o.category, Ordering.natural().nullsFirst())
                .compare(quantityPerUnit, o.quantityPerUnit, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .compare(unitPrice, o.unitPrice, Ordering.natural().nullsFirst())
                .compare(unitsInStock, o.unitsInStock, Ordering.natural().nullsFirst())
                .compare(unitsOnOrder, o.unitsOnOrder, Ordering.natural().nullsFirst())
                .compare(reorderLevel, o.reorderLevel, Ordering.natural().nullsFirst())
                .compare(discontinued, o.discontinued, Ordering.natural().nullsFirst())
                .result();

        return diff;
    }

}
