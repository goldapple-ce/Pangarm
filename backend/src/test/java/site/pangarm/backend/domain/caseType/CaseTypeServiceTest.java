package site.pangarm.backend.domain.caseType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.caseType.entity.CaseType;
import site.pangarm.backend.fixture.CaseTypeFixture;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class CaseTypeServiceTest {
    @Autowired
    private CaseTypeService caseTypeService;

    @Nested
    @DisplayName("save 테스트")
    class validationTest{
        @Test
        void whenSuccess(){
            assertThrows(CaseTypeException.class,()->{
                caseTypeService.save(CaseTypeFixture.CASE_TYPE_1_FIXTURE.create());
            });
        }
    }
    @Nested
    @DisplayName("findByName 테스트")
    class findByNameTest{

        @DisplayName("성공")
        @Test
        void whenSuccess() {
            assertDoesNotThrow(()->{
                caseTypeService.findByName("가");
            });
        }

        @DisplayName("실패, 존재하지 않음")
        @Test
        void whenFailByNoData() {
            assertThrows(CaseTypeException.class,()->
                    caseTypeService.findByName("가나다라마바사"));
            }
    }

    @Nested
    @DisplayName("findByCode 테스트")
    class findByCode{

        @DisplayName("성공")
        @Test
        void whenSuccess() {
            assertDoesNotThrow(()->{
                caseTypeService.findByCode(1);
            });
        }

        @DisplayName("실패, 존재하지 않음")
        @Test
        void whenFailByNoData() {
            assertThrows(CaseTypeException.class,()->
                    caseTypeService.findByCode(1000));
        }
    }
}