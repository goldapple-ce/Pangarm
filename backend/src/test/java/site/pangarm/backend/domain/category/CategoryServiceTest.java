package site.pangarm.backend.domain.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.category.dto.request.CategoryRegisterRequest;
import site.pangarm.backend.global.error.ErrorCode;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class CategoryServiceTest {


    @Autowired
    private CategoryService categoryService;

    @Autowired CategoryRepository categoryRepository;

    @Nested
    @DisplayName("save 테스트")
    class saveTest{

        @Test
        @DisplayName("성공")
        void whenSuccess() {
            Category category = Category.of("법률개정");
            categoryService.save(category);
            Optional<Category> foundCategory = categoryRepository.findByName("법률개정");
            assertTrue(foundCategory.isPresent());
        }

        @Test()
        @DisplayName("실패, 이미 존재하는 카테고리명")
        void whenFail(){
            //given
            Category category = Category.of("법률개정");
            categoryService.save(category);

            //when, then
            assertThrows(CategoryException.class,()->{
                categoryService.save(Category.of("법률개정"));
            });
        }

    }

    @Nested
    @DisplayName("delete 테스트")
    class deleteTest{

        @Test
        @DisplayName("성공")
        void whenSuccess() {
            //GIVEN
            Category category = categoryService.save(Category.of("첫 번째 카테고리"));

            //WHEN
            assertDoesNotThrow(()-> {
                categoryService.delete(category.getId());
            });

            //THEN
            Optional<Category> deletedCategory = categoryRepository.findByName("첫 번째 카테고리");
            assertTrue(deletedCategory.isEmpty());
        }

        @Test
        @DisplayName("실패, 존재하지 않음")
        void whenFailByNotFound() {
            assertThrows(CategoryException.class,()->{
                categoryService.delete(1);
            });
        }
    }

    @Nested
    @DisplayName("update 테스트")
    class updateTest{

        @Test
        @DisplayName("성공")
        void whenSuccess() {
            // GIVEN
            Category originalCategory = categoryService.save(Category.of("첫 번째 카테고리"));
            String updatedName = "새로운 카테고리 이름";

            Category requestCategory = Category.of(updatedName);
            System.out.println(requestCategory.getId());
            // WHEN
            Category updatedCategory = categoryService.update(originalCategory.getId(), requestCategory);

            // THEN
            assertNotNull(updatedCategory);
            assertEquals(updatedName, updatedCategory.getName());

        }

        @Test
        @DisplayName("실패, 이미 존재하는 카테고리명")
        void whenFailByAlreadyExistName() {

            //GIVEN
            categoryService.save(Category.of("카테고리1"));
            Category category = categoryService.save(Category.of("카테고리2"));

            //WHEN, THEN
            CategoryException exception = assertThrows(CategoryException.class,()->{
                categoryService.update(category.getId(), Category.of("카테고리1"));
            });
            assertEquals(exception.getErrorCode(), ErrorCode.API_ERROR_ALREADY_EXIST);
        }

        @Test
        @DisplayName("실패, 존재하지 않는 아이디에 접근")
        void whenFailByNotFound(){

            //GIVEN
            Category updateRequest = Category.of("존재하지 않지만 업데이트 해보기");

            int notExistId = 999;

            assertTrue(categoryRepository.findById(notExistId).isEmpty());

            //WHEN, THEN
            CategoryException exception = assertThrows(CategoryException.class, () -> {
                categoryService.update(notExistId, updateRequest);
            });

            assertEquals(ErrorCode.API_ERROR_NOT_FOUND, exception.getErrorCode());
        }
    }

}