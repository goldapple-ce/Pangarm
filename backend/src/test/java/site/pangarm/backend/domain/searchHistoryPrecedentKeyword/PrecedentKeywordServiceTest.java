package site.pangarm.backend.domain.searchHistoryPrecedentKeyword;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.caseType.entity.CaseType;
import site.pangarm.backend.domain.caseType.CaseTypeService;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.member.MemberService;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.precedent.PrecedentService;
import site.pangarm.backend.domain.precedentType.entity.PrecedentType;
import site.pangarm.backend.domain.precedentType.PrecedentTypeService;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;
import site.pangarm.backend.domain.searchHistory.SearchHistoryService;
import site.pangarm.backend.domain.searchHistoryPrecedent.SearchHistoryPrecedentService;
import site.pangarm.backend.fixture.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class PrecedentKeywordServiceTest {
    @Autowired
    private PrecedentKeywordService precedentKeywordService;
    @Autowired
    private SearchHistoryService searchHistoryService;
    @Autowired
    private SearchHistoryPrecedentService searchHistoryPrecedentService;
    @Autowired
    private PrecedentService precedentService;
    @Autowired
    private PrecedentTypeService precedentTypeService;
    @Autowired
    private CaseTypeService caseTypeService;
    @Autowired
    private MemberService memberService;

    private SearchHistory searchHistory;

    private void setUp(){
        Member member = memberService.save(MemberFixture.MEMBER_FIXTURE.create());
        searchHistory = searchHistoryService.save(SearchHistoryFixture.SEARCH_HISTORY_FIXTURE.create(member).getSituation(),member);
        CaseType caseType = caseTypeService.findByName(CaseTypeFixture.CASE_TYPE_1_FIXTURE.create().getName());
        PrecedentType initialPrecedentType = PrecedentTypeFixture.PRECEDENT_TYPE_FIXTURE.create(caseType);
        PrecedentType precedentType = precedentTypeService.save(caseType,initialPrecedentType.getCourtName(),initialPrecedentType.getVerdict());
        List<Precedent> initialPrecedentList = new ArrayList<>();
        initialPrecedentList.add(PrecedentFixture.PRECEDENT_1_FIXTURE.create(precedentType));
        initialPrecedentList.add(PrecedentFixture.PRECEDENT_2_FIXTURE.create(precedentType));
        initialPrecedentList.add(PrecedentFixture.PRECEDENT_3_FIXTURE.create(precedentType));
        initialPrecedentList.add(PrecedentFixture.PRECEDENT_4_FIXTURE.create(precedentType));
        initialPrecedentList.add(PrecedentFixture.PRECEDENT_5_FIXTURE.create(precedentType));
        initialPrecedentList.add(PrecedentFixture.PRECEDENT_6_FIXTURE.create(precedentType));
        initialPrecedentList.add(PrecedentFixture.PRECEDENT_7_FIXTURE.create(precedentType));
        initialPrecedentList.add(PrecedentFixture.PRECEDENT_8_FIXTURE.create(precedentType));
        initialPrecedentList.add(PrecedentFixture.PRECEDENT_9_FIXTURE.create(precedentType));
        initialPrecedentList.add(PrecedentFixture.PRECEDENT_10_FIXTURE.create(precedentType));
        for(Precedent initialPrecedent : initialPrecedentList){
            Precedent precedent = precedentService.save(initialPrecedent.getCaseNumber().toString(),initialPrecedent.getCaseName(),initialPrecedent.getJudgementDate(),initialPrecedent.getSummary(),initialPrecedent.getPrecedentType());
            List<String> keywordList = new ArrayList<>();
            for(int i = 0; i < 6; i++) {
                keywordList.add(String.valueOf((int) (Math.random() * 10)));
            }
            precedentKeywordService.saveAll(precedent,keywordList);
            searchHistoryPrecedentService.save(searchHistory,precedent, precedent.getId());
        }
    }
    @Test
    void findAllBy() {
        setUp();
        List<String> precedentKeywordList = precedentKeywordService.findAllKeywordListBy(searchHistory);
        assertEquals(10,precedentKeywordList.size());
    }
}