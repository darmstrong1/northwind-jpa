package co.da.nw.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonProperty;

//import org.hibernate.validator.constraints.NotEmpty;
import co.da.nw.domain.Category;

import com.google.common.base.Objects;

public class CategoryDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty
    private Long id;

    @NotNull
    @Size(min = 2, max = Category.MAX_LENGTH_CATEGORY_NAME, message = "Name must be between {min} and {max} characters.")
    @JsonProperty
    private String name;

    @Size(max = Category.MAX_LENGTH_DESCRIPTION, message = "Description must be less than {max} characters.")
    @JsonProperty
    private String description;

    public CategoryDTO() {
    }

    public CategoryDTO(Long id,
            String name,
            String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .toString();
    }
}
