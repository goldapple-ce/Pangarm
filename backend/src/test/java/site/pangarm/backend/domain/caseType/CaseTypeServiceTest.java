package site.pangarm.backend.domain.caseType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class CaseTypeServiceTest {
    @Autowired
    private CaseTypeService caseTypeService;

    @Nested
    @DisplayName("findByName 테스트")
    class findByNameTest{

        @DisplayName("성공")
        @Test
        void whenSuccess() {
            CaseType caseType = caseTypeService.save(CaseType.of(1,"가","민사","민사소송"));
            CaseType foundCaseType = caseTypeService.findByName("가");
            assertEquals(caseType,foundCaseType);
        }

        @DisplayName("실패, 존재하지 않음")
        @Test
        void whenFailByNoData() {
            assertThrows(CaseTypeException.class,()->
                    caseTypeService.findByName("가"));
        }
    }

    @Nested
    @DisplayName("findByCode 테스트")
    class findByCode{

        @DisplayName("성공")
        @Test
        void whenSuccess() {
            CaseType caseType = caseTypeService.save(CaseType.of(1,"가","민사","민사소송"));
            CaseType foundCaseType = caseTypeService.findByCode(1);
            assertEquals(caseType,foundCaseType);
        }

        @DisplayName("실패, 존재하지 않음")
        @Test
        void whenFailByNoData() {
            assertThrows(CaseTypeException.class,()->
                    caseTypeService.findByCode(1));
        }
    }
}