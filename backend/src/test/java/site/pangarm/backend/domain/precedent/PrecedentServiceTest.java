package site.pangarm.backend.domain.precedent;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.caseType.CaseType;
import site.pangarm.backend.domain.caseType.CaseTypeService;
import site.pangarm.backend.domain.precedentType.PrecedentType;
import site.pangarm.backend.domain.precedentType.PrecedentTypeRepository;
import site.pangarm.backend.global.error.exception.BusinessException;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class PrecedentServiceTest {
    @Autowired
    private PrecedentService precedentService;

    @Autowired
    private PrecedentTypeRepository precedentTypeRepository;

    @Autowired
    private CaseTypeService caseTypeService;

    @Autowired
    private EntityManager em;

    @BeforeEach
    void setUp() throws IOException {
        caseTypeService.save(CaseType.of(1,"가","형사","형사소송"));
    }

    @Nested
    @DisplayName("findByCaseNumber 테스트")
    class findByCaseNumberTest{

        @Test
        @DisplayName("성공")
        void whenSuccess() {
            PrecedentType precedentType = precedentTypeRepository.save(PrecedentType.of("형사","대법원",3));
            Precedent precedent = precedentService.save("1234가4321","힘든사건",LocalDate.now(),precedentType);
            Precedent foundPrecedent = precedentService.findByCaseNumber("1234가4321");
            assertEquals(precedent,foundPrecedent);
        }

        @Test
        @DisplayName("실패, 존재하지 않음")
        void whenFailByNotFound() {
            PrecedentType precedentType = precedentTypeRepository.save(PrecedentType.of("형사","대법원",3));
            precedentService.save("1234가4321","힘든사건",LocalDate.now(),precedentType);
            assertThrows(PrecedentException.class,()->{
                precedentService.findByCaseNumber("1234가21");
            });
        }
    }

    @Nested
    @DisplayName("findById 테스트")
    class findByIdTest{

        @Test
        @DisplayName("성공")
        void whenSuccess() {
            PrecedentType precedentType = precedentTypeRepository.save(PrecedentType.of("형사","대법원",3));
            Precedent precedent = precedentService.save("1234가4321","힘든사건",LocalDate.now(),precedentType);
            em.clear();
            em.close();
            Precedent foundPrecedent = precedentService.findById(precedent.getId());
            assertEquals(precedent,foundPrecedent);
        }

        @Test
        @DisplayName("실패, 존재하지 않음")
        void whenFailByNotFound() {
            PrecedentType precedentType = precedentTypeRepository.save(PrecedentType.of("형사","대법원",3));
            Precedent precedent = precedentService.save("1234가4321","힘든사건",LocalDate.now(),precedentType);
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
                PrecedentType precedentType = precedentTypeRepository.save(PrecedentType.of("형사","대법원",3));
                precedentService.save("1234가4321","힘든사건",LocalDate.now(),precedentType);
            });
        }

        @Test
        @DisplayName("실패, 이미 존재함")
        void whenFailByAlreadyExist() {
            PrecedentType precedentType = precedentTypeRepository.save(PrecedentType.of("형사","대법원",3));
            precedentService.save("1234가4321","힘든사건",LocalDate.now(),precedentType);
            assertThrows(BusinessException.class,()->{
                precedentService.save("1234가4321","힘든사건",LocalDate.now(),precedentType);

            });
        }

        @Test
        @DisplayName("실패, 사건년도 없음")
        void whenFailByNoCaseYear() {
            assertThrows(BusinessException.class,()->{
                PrecedentType precedentType = precedentTypeRepository.save(PrecedentType.of("형사","대법원",3));
                precedentService.save("가나4321","힘든사건", LocalDate.now(),precedentType);
            });
        }

        @Test
        @DisplayName("실패, 사건부호 없음")
        void whenFailByNoCaseType() {
            assertThrows(BusinessException.class,()->{
                PrecedentType precedentType = precedentTypeRepository.save(PrecedentType.of("형사","대법원",3));
                precedentService.save("12344321","힘든사건", LocalDate.now(),precedentType);
            });
        }

        @Test
        @DisplayName("실패, 접수번호 없음")
        void whenFailByNoRegistrationNumber() {
            assertThrows(BusinessException.class,()->{
                PrecedentType precedentType = precedentTypeRepository.save(PrecedentType.of("형사","대법원",3));
                precedentService.save("1234가나","힘든사건", LocalDate.now(),precedentType);
            });
        }
    }
}