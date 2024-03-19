package site.pangarm.backend.domain.category;

import site.pangarm.backend.application.category.dto.request.CategoryRegisterRequest;
import site.pangarm.backend.application.category.dto.request.CategoryUpdateRequest;

public interface CategoryService {

    void save(CategoryRegisterRequest request);

    void delete(int categoryId);

    void update(int categoryId, CategoryUpdateRequest request);

}
