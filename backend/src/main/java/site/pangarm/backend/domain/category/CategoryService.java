package site.pangarm.backend.domain.category;

import site.pangarm.backend.application.category.dto.request.CategoryUpdateRequest;

public interface CategoryService {

    Category save(Category category);

    void delete(int categoryId);

    Category update(int id, Category category);

    boolean isExistName(String name);
}
