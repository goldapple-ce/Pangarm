package site.pangarm.backend.application.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.category.dto.request.CategoryRegisterRequest;
import site.pangarm.backend.domain.category.Category;
import site.pangarm.backend.domain.category.CategoryService;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NewsFacade {

    private final CategoryService categoryService;

    public void registerCategory(CategoryRegisterRequest request) {
        categoryService.save(request.toEntity());
    }
}
