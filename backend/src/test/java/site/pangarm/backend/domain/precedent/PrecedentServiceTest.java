package site.pangarm.backend.domain.precedent;

import jakarta.persistence.EntityManager;
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
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.precedentType.entity.PrecedentType;
import site.pangarm.backend.domain.precedentType.PrecedentTypeService;
import site.pangarm.backend.fixture.PrecedentFixture;
import site.pangarm.backend.fixture.PrecedentTypeFixture;
import site.pangarm.backend.global.error.exception.BusinessException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class PrecedentServiceTest {
    @Autowired
    private PrecedentService precedentService;

    @Autowired
    private CaseTypeService caseTypeService;

    @Autowired
    private PrecedentTypeService precedentTypeService;

    @Autowired
    private EntityManager em;

    private CaseType caseType;
    private PrecedentType precedentType;

    private Precedent createdPrecedent;

    @BeforeEach
    void setUp() {
        caseType = caseTypeService.findByCode(1);
        PrecedentType precedentType1 = PrecedentTypeFixture.PRECEDENT_TYPE_FIXTURE.create(caseType);
        precedentType = precedentTypeService.save(caseType,precedentType1.getCourtName(),precedentType1.getVerdict());
        createdPrecedent = PrecedentFixture.PRECEDENT_1_FIXTURE.create(precedentType);
    }

    @Nested
    @DisplayName("findByCaseNumber 테스트")
    class findByCaseNumberTest{

        @Test
        @DisplayName("성공")
        void whenSuccess() {
            Precedent precedent = precedentService.save(createdPrecedent.getCaseNumber().toString().toString(),createdPrecedent.getCaseName(),LocalDate.now(), createdPrecedent.getSummary(), precedentType);
            Precedent foundPrecedent = precedentService.findByCaseNumber(createdPrecedent.getCaseNumber().toString().toString());
            assertEquals(precedent,foundPrecedent);
        }

        @Test
        @DisplayName("실패, 존재하지 않음")
        void whenFailByNotFound() {
            precedentService.save(createdPrecedent.getCaseNumber().toString().toString(),createdPrecedent.getCaseName(),LocalDate.now(), createdPrecedent.getSummary(), precedentType);
            assertThrows(PrecedentException.class,()->{
                precedentService.findByCaseNumber("1234나가가21");
            });
        }
    }

    @Nested
    @DisplayName("findById 테스트")
    class findByIdTest{

        @Test
        @DisplayName("성공")
        void whenSuccess() {
            Precedent precedent = precedentService.save(createdPrecedent.getCaseNumber().toString().toString(),createdPrecedent.getCaseName(),LocalDate.now(), createdPrecedent.getSummary(), precedentType);
            em.clear();
            em.close();
            Precedent foundPrecedent = precedentService.findById(precedent.getId());
            assertEquals(precedent,foundPrecedent);
        }

        @Test
        @DisplayName("실패, 존재하지 않음")
        void whenFailByNotFound() {
            Precedent precedent = precedentService.save(createdPrecedent.getCaseNumber().toString().toString(),createdPrecedent.getCaseName(),LocalDate.now(),createdPrecedent.getSummary(),precedentType);
            em.clear();
            em.close();

            assertThrows(PrecedentException.class,()->{
                precedentService.findById(precedent.getId()+1);
            });
        }
    }

    @Nested
    @DisplayName("save 테스트")
    class saveTest{

        @Test
        @DisplayName("성공")
        void whenSuccess() {
            assertDoesNotThrow(()->{

                precedentService.save(createdPrecedent.getCaseNumber().toString(),createdPrecedent.getCaseName(),LocalDate.now(),createdPrecedent.getSummary(),precedentType);
            });
        }

        @Test
        @DisplayName("성공, 이미 존재함")
        void whenSuccessByAlreadyExist() {
            precedentService.save(createdPrecedent.getCaseNumber().toString(),createdPrecedent.getCaseName(),LocalDate.now(),createdPrecedent.getSummary(),precedentType);
            assertDoesNotThrow(()->{
                precedentService.save(createdPrecedent.getCaseNumber().toString(),createdPrecedent.getCaseName(),LocalDate.now(),createdPrecedent.getSummary(),precedentType);
            });
        }

        @Test
        @DisplayName("실패, 사건년도 없음")
        void whenFailByNoCaseYear() {
            assertThrows(BusinessException.class,()->{
    
                precedentService.save("가나4321","힘든사건", LocalDate.now(),createdPrecedent.getSummary(),precedentType);
            });
        }

        @Test
        @DisplayName("실패, 사건부호 없음")
        void whenFailByNoCaseType() {
            assertThrows(BusinessException.class,()->{
                precedentService.save("12344321","힘든사건", LocalDate.now(),createdPrecedent.getSummary(),precedentType);
            });
        }

        @Test
        @DisplayName("실패, 접수번호 없음")
        void whenFailByNoRegistrationNumber() {
            assertThrows(BusinessException.class,()->{
                precedentService.save("1234가나","힘든사건", LocalDate.now(),createdPrecedent.getSummary(),precedentType);
            });
        }
    }
}