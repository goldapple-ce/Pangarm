package site.pangarm.backend.domain.searchHistoryPrecedentKeyword;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;
import site.pangarm.backend.domain.searchHistoryPrecedentKeyword.entity.PrecedentKeyword;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PrecedentKeywordService {
    private final PrecedentKeywordRepository precedentKeywordRepository;

    @Transactional
    public List<PrecedentKeyword> saveAll(Precedent precedent, List<String> keywordList) {
        List<PrecedentKeyword> precedentKeywordList = new ArrayList<>();
        for (String keyword : keywordList) {
            precedentKeywordList.add(precedentKeywordRepository.findByPrecedentAndKeyword(precedent, keyword).orElseGet(() ->
                    precedentKeywordRepository.save(PrecedentKeyword.of(precedent, keyword))
            ));
        }
        return precedentKeywordRepository.saveAll(precedentKeywordList);
    }

    public List<String> findAllKeywordListBy(SearchHistory searchHistory) {
        return precedentKeywordRepository.findAllKeywordBySearchHistoryPrecedent_SearchHistory(searchHistory);
    }
}
