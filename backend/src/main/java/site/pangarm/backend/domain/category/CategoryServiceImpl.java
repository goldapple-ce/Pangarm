package site.pangarm.backend.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.category.entity.Category;
import site.pangarm.backend.global.error.ErrorCode;
import java.util.List;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Category save(Category category) {
        if(existsByName(category.getName())){
            throw new CategoryException(ErrorCode.API_ERROR_ALREADY_EXIST);
        }
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void delete(int id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete, ()-> {
            throw new CategoryException(ErrorCode.API_ERROR_NOT_FOUND);
        });
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    @Transactional
    @Override
    public Category update(int id, Category category) {

        // 네임 중복 여부 확인
        if(existsByName(category.getName())){
            throw new CategoryException(ErrorCode.API_ERROR_ALREADY_EXIST);
        }

        // 업데이트
        Category foundCategory =
                categoryRepository.findById(id).orElseThrow(()->new CategoryException(ErrorCode.API_ERROR_NOT_FOUND));
        return foundCategory.update(category);

    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Integer categoryId) throws CategoryException {
        return categoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryException(ErrorCode.API_ERROR_NOT_FOUND));
    }

}
