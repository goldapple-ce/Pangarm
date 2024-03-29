package site.pangarm.backend.domain.searchHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;

import java.util.List;
import java.util.Optional;

interface SearchHistoryRepository extends JpaRepository<SearchHistory, Integer> {
    Optional<SearchHistory> findBySituationAndMember(String situation, Member member);

    List<SearchHistory> findAllByMember(Member member);
}
