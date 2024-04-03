package site.pangarm.backend.domain.precedentBookmark;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.caseType.CaseTypeService;
import site.pangarm.backend.domain.caseType.entity.CaseType;
import site.pangarm.backend.domain.member.MemberService;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.precedent.PrecedentService;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.precedentBookmark.entity.PrecedentBookmark;
import site.pangarm.backend.domain.precedentType.PrecedentTypeService;
import site.pangarm.backend.domain.precedentType.entity.PrecedentType;
import site.pangarm.backend.fixture.MemberFixture;
import site.pangarm.backend.fixture.PrecedentBookmarkFixture;
import site.pangarm.backend.fixture.PrecedentFixture;
import site.pangarm.backend.fixture.PrecedentTypeFixture;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class PrecedentBookmarkServiceTest {
    @Autowired
    private PrecedentBookmarkService precedentBookmarkService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PrecedentService precedentService;

    @Autowired
    private PrecedentTypeService precedentTypeService;

    @Autowired
    private CaseTypeService caseTypeService;

    private Member member;
    private Precedent precedent;

    @BeforeEach
    void setup(){
        member = memberService.save(MemberFixture.MEMBER_FIXTURE.create());
        CaseType caseType = caseTypeService.findByCode(1);
        PrecedentType precedentType = precedentTypeService.save(PrecedentTypeFixture.PRECEDENT_TYPE_FIXTURE.create(caseType));
        precedent = precedentService.save(PrecedentFixture.PRECEDENT_1_FIXTURE.create(precedentType));
    }

    @DisplayName("save 테스트")
    @Nested
    class saveTest{

        @Test
        @DisplayName("성공")
        void whenSuccess() {

            assertDoesNotThrow(()->{
                precedentBookmarkService.save(PrecedentBookmarkFixture.PRECEDENT_BOOKMARK_FIXTURE.create(member,precedent));
            });
        }

        @Test
        @DisplayName("실패, 이미 존재함")
        void whenFailByAlreadyExist(){
            precedentBookmarkService.save(PrecedentBookmarkFixture.PRECEDENT_BOOKMARK_FIXTURE.create(member,precedent));
            assertThrows(PrecedentBookmarkException.class,()->{
                precedentBookmarkService.save(PrecedentBookmarkFixture.PRECEDENT_BOOKMARK_FIXTURE.create(member,precedent));
            });

        }
    }

    @DisplayName("findById 테스트")
    @Nested
    class findByIdTest{

        @DisplayName("성공")
        @Test
        void whenSuccess() {
            PrecedentBookmark precedentBookmark = precedentBookmarkService.save(PrecedentBookmarkFixture.PRECEDENT_BOOKMARK_FIXTURE.create(member,precedent));
            assertDoesNotThrow(()->{
                precedentBookmarkService.findById(precedentBookmark.getId());
            });
        }


        @DisplayName("실패, 존재하지 않음")
        @Test
        void whenFailByNotExist() {
            assertThrows(PrecedentBookmarkException.class,()->{
                    precedentBookmarkService.findById(1000);
            });
        }
    }

}