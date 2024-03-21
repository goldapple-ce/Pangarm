package site.pangarm.backend.domain.membercategory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.category.Category;
import site.pangarm.backend.domain.category.CategoryService;
import site.pangarm.backend.domain.member.Member;
import site.pangarm.backend.domain.member.MemberService;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@Rollback(value = false)
@SpringBootTest
@ActiveProfiles("test")
class MemberCategoryServiceTest {

    @Autowired
    private MemberCategoryService memberCategoryService;
    
    @Autowired
    private MemberCategoryRepository memberCategoryRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CategoryService categoryService;

    @Nested
    @DisplayName("회원 카테고리 테스트")
    class SaveTest{
        
        @Test
        @DisplayName("성공")
        void whenSuccess() {

            //GIVEN
            Member joinMember = memberService.save(
                    Member.builder()
                        .email("12345")
                        .password("12345")
                        .name("123")
                        .gender(1)
                        .job("학생")
                        .build());

            Category savedCategory = categoryService.save(
                    Category.builder()
                            .name("test")
                            .build());

            //WHEN,THEN
            assertDoesNotThrow(()->{
                memberCategoryService.save(joinMember.getId(), savedCategory.getId());
            });
        }

    }
    

    @Nested
    @DisplayName("회원 카테고리 삭제 테스트")
    class DeleteTest{

        @Test
        @DisplayName("성공")
        void whenSuccess() {
            //GIVEN
            Member joinMember = memberService.save(
                    Member.builder()
                            .email("12345")
                            .password("12345")
                            .name("123")
                            .gender(1)
                            .job("학생")
                            .build());

            Category savedCategory = categoryService.save(
                    Category.builder()
                            .name("test")
                            .build());

            memberCategoryService.save(joinMember.getId(), savedCategory.getId());

            //WHEN, THEN
            assertDoesNotThrow(()->{
                    memberCategoryService.delete(joinMember.getId(), savedCategory.getId());
            });
        }
    }
}