package site.pangarm.backend.application.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.client.PrecedentFetchAPI;
import site.pangarm.backend.application.dto.response.PrecedentSearchDetailClientResponse;
import site.pangarm.backend.application.dto.response.PrecedentSearchSummaryClientResponse;
import site.pangarm.backend.application.client.SearchPrecedentClientResponse;
import site.pangarm.backend.application.dto.request.PrecedentSearchRequest;
import site.pangarm.backend.application.dto.response.*;
import site.pangarm.backend.domain.caseType.CaseTypeService;
import site.pangarm.backend.domain.caseType.entity.CaseType;
import site.pangarm.backend.domain.member.MemberService;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.precedent.PrecedentService;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.precedentBookmark.PrecedentBookmarkService;
import site.pangarm.backend.domain.precedentType.PrecedentTypeService;
import site.pangarm.backend.domain.precedentType.entity.PrecedentType;
import site.pangarm.backend.domain.searchHistory.SearchHistoryService;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;
import site.pangarm.backend.domain.searchHistoryPrecedent.SearchHistoryPrecedentService;
import site.pangarm.backend.domain.searchHistoryPrecedentKeyword.PrecedentKeywordService;
import site.pangarm.backend.domain.viewingHistory.ViewingHistoryService;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
@Slf4j
public class PrecedentFacade {
    private final PrecedentService precedentService;
    private final PrecedentTypeService precedentTypeService;
    private final PrecedentFetchAPI precedentFetchAPI;
    private final CaseTypeService caseTypeService;
    private final SearchHistoryService searchHistoryService;
    private final SearchHistoryPrecedentService searchHistoryPrecedentService;
    private final MemberService memberService;
    private final PrecedentKeywordService precedentKeywordService;
    private final PrecedentBookmarkService precedentBookmarkService;
    private final ViewingHistoryService viewingHistoryService;

    @Transactional
    public PrecedentSearchResponse searchPrecedent(User user, PrecedentSearchRequest request, Pageable page) {
        Member member = user == null ? null : memberService.findById(Integer.parseInt(user.getUsername()));
        SearchHistory searchHistory = searchHistoryService.save(request.getSituation(), member);
        if (searchHistory.getSearchHistoryPrecedentList().isEmpty()) {
            SearchPrecedentClientResponse response = precedentFetchAPI.fetchAPI(request);
            for (SearchPrecedentClientResponse.Precedent precedentVO : response.getPrecedentList()) {
                savePrecedentHistory(searchHistory, precedentVO);
            }
        }
        Page<Object[]> searchHistoryPrecedentPage = searchHistoryPrecedentService.findAllWithIsViewedBySearchHistory(searchHistory,request.toOption(), page);

        List<String> keywordList = precedentKeywordService.findAllKeywordListBy(searchHistory);
        return PrecedentSearchResponse.of(searchHistoryPrecedentPage, keywordList);
    }

    private void savePrecedentHistory(SearchHistory searchHistory, SearchPrecedentClientResponse.Precedent precedentVO) {
        if(caseTypeService.existsByName(precedentVO.getInfo().getCaseTypeName())){
            CaseType caseType = caseTypeService.findByName(precedentVO.getInfo().getCaseTypeName());
            PrecedentType precedentType = precedentTypeService.save(caseType, precedentVO.getInfo().getType().getCourtName(), precedentVO.getInfo().getType().getVerdict());
            Precedent precedent = precedentService.save(precedentVO.getInfo().getCaseNumber(), precedentVO.getInfo().getCaseName(), precedentVO.getInfo().getJudgementDate(), precedentVO.getSummary(), precedentType);
            precedentKeywordService.saveAll(precedent, precedentVO.getKeywordList());
            searchHistoryPrecedentService.save(searchHistory, precedent, precedentVO.getScore());
        }
    }

    public PrecedentBookmarkedResponse getBookmarkedPrecedent(User user, Pageable pageable) {
        Member member = memberService.findByUser(user);
        Page<Object[]> precedentBookmarkPage = precedentBookmarkService.findByMember(member, pageable);
        return PrecedentBookmarkedResponse.of(precedentBookmarkPage);
    }

    public PrecedentViewedResponse getViewedPrecedent(User user, Pageable pageable) {
        Member member = memberService.findByUser(user);
        Page<Object[]> viewedPrecedentPage = viewingHistoryService.findByMember(member, pageable);
        return PrecedentViewedResponse.of(viewedPrecedentPage);
    }

    public PrecedentSearchSummaryClientResponse getPrecedentSummary(User user, String caseNumber) {
        Member member = memberService.findByUser(user);
        Precedent precedent = precedentService.findByCaseNumber(caseNumber);
        viewingHistoryService.save(member,precedent);
        PrecedentSearchSummaryClientResponse response = precedentFetchAPI.fetchAPI("/summary?caseNumber=",caseNumber, PrecedentSearchSummaryClientResponse.class);
        log.info(response.getConclusion());
        return response;
    }

    public PrecedentSearchDetailClientResponse getPrecedentDetail(User user, String caseNumber) {
        Member member = memberService.findByUser(user);
        Precedent precedent = precedentService.findByCaseNumber(caseNumber);
        viewingHistoryService.save(member,precedent);
        return precedentFetchAPI.fetchAPI("/detail?caseNumber=",caseNumber, PrecedentSearchDetailClientResponse.class);
    }


}
