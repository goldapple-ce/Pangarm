package site.pangarm.backend.domain.searchHistoryPrecedent;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.dto.request.PrecedentSearchRequest;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;
import site.pangarm.backend.domain.searchHistoryPrecedent.entity.SearchHistoryPrecedent;
import site.pangarm.backend.global.error.ErrorCode;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SearchHistoryPrecedentService {
    private final SearchHistoryPrecedentRepository searchHistoryPrecedentRepository;

    @Transactional
    public SearchHistoryPrecedent save(SearchHistory searchHistory, Precedent precedent, double score){
        return searchHistoryPrecedentRepository.save(validation(searchHistory,precedent,score));
    }

    public Page<Object[]> findAllWithIsViewedBySearchHistory(SearchHistory searchHistory, SearchHistoryOption option, Pageable page){
        if(option.getKeywordList() == null || option.getKeywordList().isEmpty())
            return searchHistoryPrecedentRepository.findDistinctByOption(searchHistory,option,page);
        return searchHistoryPrecedentRepository.findDistinctByOptionWithKeywordList(searchHistory,option,page);
    }

    private SearchHistoryPrecedent validation(SearchHistory searchHistory, Precedent precedent,double score){
        if(searchHistoryPrecedentRepository.existsBySearchHistoryAndPrecedent(searchHistory,precedent)){
            throw new SearchHistoryPrecedentException(ErrorCode.API_ERROR_ALREADY_EXIST);
        }
        return SearchHistoryPrecedent.of(searchHistory,precedent,score);
    }
}
