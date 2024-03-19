package site.pangarm.backend.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.pangarm.backend.application.category.dto.request.CategoryRegisterRequest;
import site.pangarm.backend.application.category.dto.request.CategoryUpdateRequest;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Override
    public void save(CategoryRegisterRequest request) {
        categoryRepository.save(request.toEntity());
    }

    @Override
    public void delete(int categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Override
    public void update(int categoryId, CategoryUpdateRequest request) {
        Category foundCategory = categoryRepository.findById(categoryId).orElseThrow(IllegalArgumentException::new);
        foundCategory.update(request);
    }
}
