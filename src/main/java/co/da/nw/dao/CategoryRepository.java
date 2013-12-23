package co.da.nw.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import co.da.nw.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findByCategoryName(String categoryName);

    Page<Category> findByCategoryNameLike(String categoryName, Pageable pageable);

    Page<Category> findByDescriptionLike(String description, Pageable pageable);

    Page<Category> findByCategoryNameLikeAndDescriptionLike(String categoryName, String description, Pageable pageable);
}
