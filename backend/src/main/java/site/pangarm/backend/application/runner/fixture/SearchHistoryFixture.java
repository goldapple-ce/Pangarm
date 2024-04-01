package site.pangarm.backend.application.runner.fixture;

import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;

public enum SearchHistoryFixture {
    SEARCH_HISTORY_FIXTURE("오늘은 제가 사람을 죽인지 15년이 되는날이에요. 내년이면 사건에 대해서 풀릴까요?")

    ;
    private final String situation;

    SearchHistoryFixture(String situation) {
        this.situation = situation;
    }

    public SearchHistory create(){
        return SearchHistory.of( situation, MemberFixture.MEMBER_FIXTURE.create());
    }

    public SearchHistory create(Member member){
        return SearchHistory.of(situation,member);
    }
}
