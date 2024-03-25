package site.pangarm.backend.domain.membercategory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.category.Category;
import site.pangarm.backend.domain.category.CategoryService;
import site.pangarm.backend.domain.member.MemberService;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.membercategory.entity.MemberCategory;
import site.pangarm.backend.domain.member.Member;
import site.pangarm.backend.domain.member.MemberService;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
//@Rollback(value = false)
@SpringBootTest
@ActiveProfiles("test")
class MemberCategoryServiceTest {

    @Autowired
    private MemberCategoryService memberCategoryService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CategoryService categoryService;


    @Nested
    @DisplayName("회원 카테고리 저장 테스트")
    class SaveTest{
        private Member joinMember;

        @BeforeEach
        void setUp(){
            joinMember = memberService.save(Member.of("12345", "12345", "123", 1, 17, "학생"));
        }
        
        @Test
        @DisplayName("성공")
        void whenSuccess() {

            Category savedCategory = categoryService.save(Category.of("name"));

            assertDoesNotThrow(()->{
                memberCategoryService.save(joinMember, savedCategory);
            });
        }

        @Test
        @DisplayName("실패_이미 존재하는 경우")
        void whenFail(){

            Category savedCategory = categoryService.save(Category.of("name"));
            memberCategoryService.save(joinMember, savedCategory);

            assertThrows(MemberCategoryException.class, ()->{
                memberCategoryService.save(joinMember, savedCategory);
            });
        }
    }

    @Nested
    @DisplayName("회원 카테고리 삭제 테스트")
    class DeleteTest{

        @Test
        @DisplayName("성공")
        void whenSuccess() {

            Member joinMember = memberService.save(Member.of("12345", "12345", "123", 1, 17,"학생"));
            Category savedCategory = categoryService.save(Category.of("name"));
            memberCategoryService.save(joinMember, savedCategory);

            assertDoesNotThrow(()->{
                    memberCategoryService.delete(joinMember.getId(), savedCategory.getId());
            });
        }
    }

    @Nested
    @DisplayName("회원 아이디와 회원 카테고리 아이디로 조회")
    class findByMemberIdAndCategoryIdTest {

        @Test
        @DisplayName("성공_존재할 경우")
        void whenSuccessAndResultsExist() {
            //GIVEN
            Member joinMember = memberService.save(Member.of("12345", "12345", "123", 1, 17, "학생"));

            Category savedCategory = categoryService.save(Category.of("name"));

            memberCategoryService.save(joinMember, savedCategory);

            assertDoesNotThrow(() -> {
                MemberCategory memberCategory = memberCategoryService.findByMemberIdAndCategoryId(joinMember.getId(), savedCategory.getId());
                assertNotNull(memberCategory);
            });
        }

        @Test
        @DisplayName("성공_존재하지 않을 경우")
        void whenSuccessAndResultsNotExist() {
            assertDoesNotThrow(() -> {
                MemberCategory memberCategory = memberCategoryService.findByMemberIdAndCategoryId(999, 999);
                assertNull(memberCategory);
            });
        }
    }
}