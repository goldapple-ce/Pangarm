package site.pangarm.backend.application.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.client.PrecedentClientResponse;
import site.pangarm.backend.application.client.PrecedentFetchAPI;
import site.pangarm.backend.application.dto.request.PrecedentSearchRequest;
import site.pangarm.backend.application.dto.response.PrecedentSearchResponse;
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
import site.pangarm.backend.domain.searchHistoryPrecedentKeyword.PrecedentKeywordService;
import site.pangarm.backend.global.error.exception.BusinessException;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PrecedentFacade {
    private final PrecedentService precedentService;
    private final PrecedentTypeService precedentTypeService;
    private final PrecedentFetchAPI precedentFetchAPI;
    private final CaseTypeService caseTypeService;
    private final SearchHistoryService searchHistoryService;
    private final SearchHistoryPrecedentService searchHistoryPrecedentService;
    private final MemberService memberService;
    private final PrecedentKeywordService precedentKeywordService;

    @Transactional
    public PrecedentSearchResponse searchPrecedent(User user, PrecedentSearchRequest request, Pageable page){
        Member member = user == null ? null : memberService.findById(Integer.parseInt(user.getUsername()));
        SearchHistory searchHistory = searchHistoryService.save(request.getSituation(), member);
        if(searchHistory.getSearchHistoryPrecedentList().isEmpty()){
            PrecedentClientResponse response = precedentFetchAPI.fetchAPI(request);
            savePrecedentHistory(searchHistory,response);
        }
        Page<Object[]> searchHistoryPrecedentPage = searchHistoryPrecedentService.findAllWithIsViewedBySearchHistory(searchHistory,page);
        List<String> keywordList = precedentKeywordService.findAllKeywordListBy(searchHistory);
        return PrecedentSearchResponse.of(searchHistoryPrecedentPage,keywordList);
    }

    private void savePrecedentHistory(SearchHistory searchHistory ,PrecedentClientResponse response){
        try{
            for(PrecedentClientResponse.Precedent precedentVO: response.getPrecedentList()){
                CaseType caseType = caseTypeService.findByName(precedentVO.getInfo().getCaseTypeName());
                PrecedentType precedentType = precedentTypeService.save(caseType,precedentVO.getInfo().getType().getCourtName(),precedentVO.getInfo().getType().getVerdict());
                Precedent precedent = precedentService.save(precedentVO.getInfo().getCaseNumber(),precedentVO.getInfo().getCaseName(),precedentVO.getInfo().getJudgementDate(), precedentVO.getSummary(),precedentType);
                precedentKeywordService.saveAll(precedent,precedentVO.getKeywordList());
                searchHistoryPrecedentService.save(searchHistory,precedent,precedentVO.getScore());
            }
        }catch (BusinessException ignored){}
    }
}
