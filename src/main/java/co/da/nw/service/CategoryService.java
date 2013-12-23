package co.da.nw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import co.da.nw.domain.Category;

public interface CategoryService {

    Category create(Category cat);

    Category delete(Long id);

    List<Category> findAll();

    Page<Category> findAll(Pageable pageable);

    Category findById(Long id);

    List<Category> findByName(String name);

    Category update(Long id, String categoryName, String description);

    Page<Category> findByNameLike(String name, Pageable pageable);

    Page<Category> findByNameLikeAndDescriptionLike(String name, String description, Pageable pageable);

    Page<Category> findByDescriptionLike(String description, Pageable pageable);

}
