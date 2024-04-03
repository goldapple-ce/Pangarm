package site.pangarm.backend.domain.precedentType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.caseType.entity.CaseType;
import site.pangarm.backend.domain.caseType.CaseTypeService;
import site.pangarm.backend.domain.precedentType.entity.PrecedentType;

import static org.junit.jupiter.api.Assertions.*;
import static site.pangarm.backend.fixture.PrecedentTypeFixture.PRECEDENT_TYPE_FIXTURE;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class PrecedentTypeServiceTest {
    @Autowired
    private PrecedentTypeService precedentTypeService;

    @Autowired
    private CaseTypeService caseTypeService;

    private CaseType caseType;
    private PrecedentType precedentType;

    @BeforeEach
    void setUp(){
        caseType = caseTypeService.findByCode(1);
        precedentType = PRECEDENT_TYPE_FIXTURE.create(caseType);
    }

    @Nested
    @DisplayName("save 테스트")
    class saveTest{

        @DisplayName("성공")
        @Test
        void whenSuccess() {
            assertDoesNotThrow(()->{
                precedentTypeService.save(precedentType.getCaseType(),precedentType.getCourtName(),precedentType.getVerdict());
            });
        }

        @DisplayName("성공, 이미 존재 하는 데이터여도")
        @Test
        void whenSuccessEvenIfAlreadyExist() {
            assertDoesNotThrow(()->{
                precedentTypeService.save(precedentType.getCaseType(),precedentType.getCourtName(),precedentType.getVerdict());
                precedentTypeService.save(precedentType.getCaseType(),precedentType.getCourtName(),precedentType.getVerdict());

            });
        }
    }


    @DisplayName("findBy 테스트")
    @Nested
    class findByTest{

        @DisplayName("성공")
        @Test
        void whenSuccess() {
            precedentTypeService.save(precedentType.getCaseType(),precedentType.getCourtName(),precedentType.getVerdict());
            assertDoesNotThrow(()->{
                precedentTypeService.findBy(caseType,"대법원","선고");
            });
        }

        @DisplayName("실패, 존재하지 않음")
        @Test
        void whenFailByNotExist() {
            assertThrows(PrecedentTypeException.class,()->{
                precedentTypeService.findBy(caseType,"대법원","선고");
            });
        }
    }
}