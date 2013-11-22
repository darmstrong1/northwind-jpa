package co.da.nw.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.da.nw.dao.CategoryRepository;
import co.da.nw.domain.Category;
import co.da.nw.dto.CategoryDTO;

import javax.persistence.EntityNotFoundException;

@Service
public class RepositoryCategoryService implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryCategoryService.class);
    
    @Resource
    private CategoryRepository repository;

    @Transactional
    @Override
    public Category create(CategoryDTO created) {
        LOGGER.debug("Creating a new category with information: " + created);
        
        Category cat = new Category.Builder().setDescription(created.getDescription())
                .setPicture(created.getPicture())
                .build(created.getCategoryName());
        return repository.save(cat);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public Category delete(Long id) throws EntityNotFoundException {
        LOGGER.debug("Deleting category with id: " + id);
        
        Category deleted = repository.findOne(id);
        
        if(deleted == null) {
            LOGGER.debug("No category found with id: " + id);
            throw new EntityNotFoundException(Category.class.getSimpleName() + " not found with id of " + id);
        }
        
        repository.delete(deleted);
        
        return deleted;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> findAll() {
        LOGGER.debug("Finding all categories");
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Category findById(Long id) {
        LOGGER.debug("Finding category by id: " + id);
        return repository.findOne(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Category> findByName(String name) {
        LOGGER.debug("Finding categories by name: " + name);
        return repository.findByCategoryName(name);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public Category update(CategoryDTO updated) {
        LOGGER.debug("Updating category with information: " + updated);
        
        Category cat = repository.findOne(updated.getCategoryId());
        if (cat == null) {
            LOGGER.debug("No category found with id: " + updated.getCategoryId());
            throw new EntityNotFoundException(Category.class.getSimpleName() + " not found with id of " + updated.getCategoryId()); 
        }
        
        Category.Builder builder = new Category.Builder();
        builder.setDescription(updated.getDescription());
        builder.setPicture(updated.getPicture());
        
        Category update = builder.buildUpdate(cat, updated.getCategoryName());
        
        return repository.save(update);
    }

}
