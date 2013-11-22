package co.da.nw.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import co.da.nw.domain.Category;

public class CategoryDTO {
    
    private Long categoryId;
    
    @NotEmpty
    @Length(max = Category.MAX_LENGTH_CATEGORY_NAME)
    private String categoryName;
    
    @Length(max = Category.MAX_LENGTH_DESCRIPTION)
    private String description;
    
    private byte[] picture;
    
    public CategoryDTO() {}
    
    public Long getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }
    public String getDescription() { return description; }
    public byte[] getPicture() { return picture == null ? picture : picture.clone(); }
    
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public void setDescription(String description) { this.description = description; }
    public void setPicture(byte[] picture) { this.picture = picture == null ? picture : picture.clone(); }
    
    
    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
            .add("categoryId", categoryId)
            .add("categoryName", categoryName)
            .add("description", description)
            .add("picture", picture)
            .toString();
    }
    

}
