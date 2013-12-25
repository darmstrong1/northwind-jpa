package co.da.nw.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.da.nw.dao.CategoryRepository;
import co.da.nw.domain.Category;
import co.da.nw.dto.CategoryDTO;

@Service
public class RepositoryCategoryService implements CategoryService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(RepositoryCategoryService.class);

    @Resource
    private CategoryRepository repository;

    @Transactional
    @Override
    public Category create(CategoryDTO dto) {
        LOGGER.debug("Creating a new category with information: " + dto);

        Category created = new Category.Builder(dto.getName())
                .setDescription(dto.getDescription())
                .build();
        return repository.save(created);
    }

    // @Transactional
    // @Override
    // public Category create(JsonCategory created) {
    // LOGGER.debug("Creating a new category with information: " + created);
    //
    // Category cat = new Category.Builder()
    // .setDescription(created.getDescription())
    // .setPicture(created.getPicture())
    // .build(created.getCategoryName());
    // return repository.save(cat);
    // }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public Category delete(Long id) throws EntityNotFoundException {
        LOGGER.debug("Deleting category with id: " + id);

        Category deleted = repository.findOne(id);

        if (deleted == null) {
            LOGGER.debug("No category found with id: " + id);
            throw new EntityNotFoundException(Category.class.getSimpleName()
                    + " not found with id of " + id);
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
    public Page<Category> findAll(Pageable pageable) {
        LOGGER.debug("Finding all categories");
        return repository.findAll(pageable);
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

    @Transactional(readOnly = true)
    @Override
    public Page<Category> findByNameLike(String name, Pageable pageRequest) {
        LOGGER.debug("Finding categories by name like: " + name);
        return repository.findByCategoryNameLike(name, pageRequest);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Category> findByNameLikeAndDescriptionLike(String name, String description, Pageable pageRequest) {
        LOGGER.debug("Finding categories by name like: " + name + " and descriptin like " + description);
        return repository.findByCategoryNameLikeAndDescriptionLike(name, description, pageRequest);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Category> findByDescriptionLike(String description, Pageable pageRequest) {
        LOGGER.debug("Finding categories by description like: " + description);
        return repository.findByDescriptionLike(description, pageRequest);
    }

    @Transactional(rollbackFor = EntityNotFoundException.class)
    @Override
    public Category update(CategoryDTO dto) {
        LOGGER.debug("Updating category with information: " + dto);

        Category existing = repository.findOne(dto.getId());

        if (existing == null) {
            LOGGER.debug("No category found with id: " + dto.getId());
            throw new EntityNotFoundException(Category.class.getSimpleName() + " not found with id of "
                    + dto.getId());
        }

        Category.Builder builder = new Category.Builder(existing, dto.getName());
        builder.setDescription(dto.getDescription());

        return repository.save(builder.build());
    }

    // @Transactional(rollbackFor = EntityNotFoundException.class)
    // @Override
    // public Category update(JsonCategory updated) {
    // LOGGER.debug("Updating category with information: " + updated);
    //
    // Category cat = repository.findOne(updated.getCategoryId());
    // if (cat == null) {
    // LOGGER.debug("No category found with id: "
    // + updated.getCategoryId());
    // throw new EntityNotFoundException(Category.class.getSimpleName()
    // + " not found with id of " + updated.getCategoryId());
    // }
    //
    // Category.Builder builder = new Category.Builder();
    // builder.setDescription(updated.getDescription());
    // builder.setPicture(updated.getPicture());
    //
    // Category update = builder.buildUpdate(cat, updated.getCategoryName());
    //
    // return repository.save(update);
    // }

}
