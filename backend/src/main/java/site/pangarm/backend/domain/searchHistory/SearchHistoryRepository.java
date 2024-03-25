package site.pangarm.backend.domain.searchHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;

import java.util.Optional;

interface SearchHistoryRepository extends JpaRepository<SearchHistory, Integer> {
    Optional<SearchHistory> findBySituationAndMember(String situation, Member member);
}
