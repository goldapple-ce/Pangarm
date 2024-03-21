package site.pangarm.backend.domain.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.global.error.ErrorCode;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public Category save(Category category) {
        if(isExistName(category.getName())){
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

    @Transactional
    @Override
    public Category update(int id, Category category) {

        // 네임 중복 여부 확인
        if(isExistName(category.getName())){
            throw new CategoryException(ErrorCode.API_ERROR_ALREADY_EXIST);
        }

        // 업데이트
        Category foundCategory =
                categoryRepository.findById(id).orElseThrow(()->new CategoryException(ErrorCode.API_ERROR_NOT_FOUND));
        return foundCategory.update(category);

    }

    @Override
    public boolean isExistName(String name) {
        return categoryRepository.existsByName(name);
    }

}
