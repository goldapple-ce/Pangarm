package site.pangarm.backend.domain.category;

import site.pangarm.backend.domain.category.entity.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    void delete(int categoryId);

    Category update(int id, Category category);

    boolean existsByName(String name);

    List<Category> findAll();

    Category findById(Integer categoryId) throws CategoryException;
}
