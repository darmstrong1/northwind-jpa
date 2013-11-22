package co.da.nw.service;

import java.util.List;

import co.da.nw.domain.Category;
import co.da.nw.dto.CategoryDTO;

public interface CategoryService {
    
    public Category create(CategoryDTO created);
    
    public Category delete(Long id);
    
    public List<Category> findAll();
    
    public Category findById(Long id);
    
    public List<Category> findByName(String name);
    
    public Category update(CategoryDTO updated);

}
