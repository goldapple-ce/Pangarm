package site.pangarm.backend.domain.searchHistory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;
import site.pangarm.backend.global.error.ErrorCode;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class SearchHistoryService {
    private final SearchHistoryRepository searchHistoryRepository;

    @Transactional
    public SearchHistory save(String situation, Member member) {
        return searchHistoryRepository.findBySituationAndMember(situation, member).orElseGet(() ->
                searchHistoryRepository.save(SearchHistory.of(situation, member))
        );
    }

    public SearchHistory findBySituationAndMember(String situation, Member member) {
        return searchHistoryRepository.findBySituationAndMember(situation, member)
                .orElseThrow(() -> SearchHistoryException.of(ErrorCode.API_ERROR_NOT_FOUND));
    }

    public List<SearchHistory> findAllByMember(Member member) {
        return searchHistoryRepository.findAllByMember(member);
    }

    public SearchHistory findById(int id) {
        return searchHistoryRepository.findById(id).orElseThrow(() ->
                new SearchHistoryException(ErrorCode.API_ERROR_NOT_FOUND));
    }

    @Transactional
    public void deleteById(int id) {
        searchHistoryRepository.deleteById(id);
    }
}
