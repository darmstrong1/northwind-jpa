package co.da.nw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import co.da.nw.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	public List<Category> findByCategoryName(String categoryName);
}
