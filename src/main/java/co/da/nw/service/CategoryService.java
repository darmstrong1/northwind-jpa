package co.da.nw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.da.nw.domain.Category;
import co.da.nw.dto.CategoryDTO;

public interface CategoryService {

    Category create(CategoryDTO dto);

    Category delete(Long id);

    List<Category> findAll();

    Page<Category> findAll(Pageable pageable);

    Category findById(Long id);

    List<Category> findByName(String name);

    Category update(CategoryDTO dto);

    Page<Category> findByNameLike(String name, Pageable pageable);

    Page<Category> findByNameLikeAndDescriptionLike(String name, String description, Pageable pageable);

    Page<Category> findByDescriptionLike(String description, Pageable pageable);

}
