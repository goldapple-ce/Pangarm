package site.pangarm.backend.domain.searchHistory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.member.MemberService;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;
import site.pangarm.backend.fixture.MemberFixture;
import site.pangarm.backend.fixture.SearchHistoryFixture;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class SearchHistoryServiceTest {

    @Autowired
    private SearchHistoryService searchHistoryService;

    @Autowired
    private MemberService memberService;

    @Nested
    @DisplayName("save 테스트")
    class SaveTest{

        @Test
        @DisplayName("성공")
        void whenSuccess() {
            Member member = memberService.save(MemberFixture.MEMBER_FIXTURE.create());

            assertDoesNotThrow(()->{
                searchHistoryService.save(SearchHistoryFixture.SEARCH_HISTORY_FIXTURE.create(member).getSituation(),member);
            });
        }
    }

    @Nested
    @DisplayName("deleteById 테스트")
    class deleteByIdTest{

        @Test
        @DisplayName("성공")
        void whenSuccess() {
            Member member = memberService.save(MemberFixture.MEMBER_FIXTURE.create());
            SearchHistory searchHistory = searchHistoryService.save(SearchHistoryFixture.SEARCH_HISTORY_FIXTURE.create(member).getSituation(),member);

            searchHistoryService.deleteById(searchHistory.getId());
        }
    }

    @Nested
    @DisplayName("findById 테스트")
    class findByIdTest{

        @Test
        @DisplayName("성공")
        void whenSuccess() {
            Member member = memberService.save(MemberFixture.MEMBER_FIXTURE.create());
            SearchHistory searchHistory = searchHistoryService.save(SearchHistoryFixture.SEARCH_HISTORY_FIXTURE.create(member).getSituation(),member);

            assertDoesNotThrow(()->{
                searchHistoryService.findById(searchHistory.getId());
            });
        }


        @Test
        @DisplayName("실패, 데이터 없음")
        void whenFailByNotFound() {
            Member member = memberService.save(MemberFixture.MEMBER_FIXTURE.create());
            SearchHistory searchHistory = searchHistoryService.save(SearchHistoryFixture.SEARCH_HISTORY_FIXTURE.create(member).getSituation(),member);

            assertThrows(SearchHistoryException.class,()->{
                searchHistoryService.findById(searchHistory.getId()+10);
            });
        }
    }
}