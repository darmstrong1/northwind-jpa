package co.da.nw.domain;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.google.common.primitives.UnsignedBytes;

@Entity
@Table(name = "categories")
public final class Category implements DomainObject, Comparable<Category> {

    public static final int MAX_LENGTH_CATEGORY_NAME = 15;
    public static final int MAX_LENGTH_DESCRIPTION = 255;

    public static class Builder {

        // This reference will save a Category object that gets passed in the constructor for updating. When build is
        // called, it will check to see if the built object is the same as this one. If it is, it will return this one
        // to avoid publishing a duplicate object.
        private final Category category;

        // Required
        private final String categoryName;
        private final Long categoryId;

        // Optional
        private String description;
        private byte[] picture;

        public Builder(String categoryName) {
            this.categoryName = categoryName;
            categoryId = null;
            category = null;
        }

        /**
         * This constructor is intended to build an update an entity that has already been saved in the database, but it
         * can also be used to build an update of an entity that has not been saved. This buildUpdate method should be
         * called if you want to update multiple elements excluding the categoryName. existing must not be null.
         * 
         * @param existing
         *            The Category object to update. Any updates should be set by calling the appropriate set method.
         */
        public Builder(Category category) {
            this(category, category.categoryName);
        }

        /**
         * This constructor is intended to build an update an entity that has already been saved in the database, but it
         * can also be used to build an update of an entity that has not been saved. This buildUpdate method should be
         * called if you want to update multiple elements including the categoryName. Update and categoryName must not
         * be null.
         * 
         * @param existing
         *            The Category object to update. Any updates should be set by calling the appropriate set method.
         * @param categoryName
         */
        public Builder(Category category, String categoryName) {
            Preconditions.checkNotNull(category, "category must not be null");
            this.categoryName = categoryName;
            categoryId = category.categoryId;
            setDescription(category.description);
            setPicture(category.picture);
            this.category = category;
        }

        public Builder setDescription(String desc) {
            description = desc;
            return this;
        }

        public Builder setPicture(byte[] picture) {
            // Make a defensive copy here.
            this.picture = picture == null ? picture : Arrays.copyOf(picture, picture.length);
            return this;
        }

        // This is called in the build class before building to ensure that a valid object gets built.
        private void validate() {
            Preconditions.checkNotNull(categoryName, "categoryName must not be null");
            Preconditions.checkArgument(categoryName.length() <= MAX_LENGTH_CATEGORY_NAME,
                    "categoryName must be less than or equal to " + MAX_LENGTH_CATEGORY_NAME + " characters.");
            if (description != null) {
                Preconditions.checkArgument(description.length() <= MAX_LENGTH_DESCRIPTION,
                        "description must be less than or equal to " + MAX_LENGTH_DESCRIPTION + " characters.");
            }
        }

        public Category build() {
            validate();
            Category built = new Category(this);
            return built.equals(category) ? category : built;
        }
    }

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "category_id")
    private final Long categoryId;

    @Column(name = "category_name", nullable = false, length = MAX_LENGTH_CATEGORY_NAME)
    private final String categoryName;

    @Column(name = "description", length = MAX_LENGTH_DESCRIPTION)
    private final String description;

    @Column(name = "picture")
    private final byte[] picture;

    @Transient
    private volatile int hashCode;

    private Category() {
        this(new Builder(""));
    }

    private Category(Builder builder) {
        Preconditions.checkNotNull(builder, "builder cannot be null");

        this.categoryId = builder.categoryId;
        this.categoryName = builder.categoryName;
        this.description = builder.description;
        this.picture = builder.picture;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getPicture() {
        return picture == null ? picture : Arrays.copyOf(picture,
                picture.length);
    }

    /**
     * This method can be used to create a new object from one that is already saved in the database if you want to
     * update it with a new category name.
     * 
     * @param categoryName
     * @return new Category object
     */
    public Category setCategoryName(String categoryName) {
        return new Category(new Builder(this, categoryName));
    }

    /**
     * This method can be used to create a new object from one that is already saved in the database if you want to
     * update it with a new description.
     * 
     * @param description
     * @return new Category object
     */
    public Category setDescription(String description) {
        return new Category(new Builder(this).setDescription(description));
    }

    /**
     * This method can be used to create a new object from one that is already saved in the database if you want to
     * update it with a new picture.
     * 
     * @param picture
     * @return new Category object
     */
    public Category setPicture(byte[] picture) {
        return new Category(new Builder(this).setPicture(picture));
    }

    /**
     * This method can be used to update a Category that exists in the database if multiple values need to be changed.
     * The Builder object has the values that should be changed. The categoryName must be passed separately because the
     * Builder does not contain that.
     * 
     * @param builder
     * @param categoryName
     * @return new Category object
     */
    // public Category update(Builder builder, String categoryName) {
    // return new Category(categoryId,
    // (categoryName == null ? this.categoryName : categoryName),
    // (builder.description == null ? description : builder.description),
    // (builder.picture == null ? picture : builder.picture));
    // }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("categoryId", categoryId)
                .add("categoryName", categoryName)
                .add("description", description)
                .add("picture", picture)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        Category c = (Category) o;

        return categoryName.equalsIgnoreCase(c.categoryName)
                && (description == null ? c.description == null : description.equalsIgnoreCase(c.description))
                && Arrays.equals(picture, c.picture);
    }

    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            // Because in equals, we compare ignoring case, we must convert the
            // Strings to upper case
            // here so that the hash code will be the same if Strings are equal
            // ignoring case.
            result = Objects.hash(categoryName.toUpperCase(),
                    (description == null ? description : description.toUpperCase()),
                    picture);
            hashCode = result;
        }
        return result;
    }

    @Override
    public int compareTo(Category o) {
        // Null is considered less than other values.
        if (o == null) {
            return 1;
        }
        int diff = ComparisonChain
                .start()
                .compare(categoryName, o.categoryName, Ordering.from(String.CASE_INSENSITIVE_ORDER))
                .compare(description, o.description, Ordering.from(String.CASE_INSENSITIVE_ORDER).nullsFirst())
                .result();
        if (diff != 0) {
            return diff;
        }

        if (picture == null) {
            return o.picture == null ? 0 : -1;
        }
        if (o.picture == null) {
            return 1;
        }
        return UnsignedBytes.lexicographicalComparator().compare(picture, o.getPicture());
    }

}
