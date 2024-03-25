package site.pangarm.backend.domain.viewingHistory;

import org.springframework.data.jpa.repository.JpaRepository;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.viewingHistory.entity.ViewingHistory;
import site.pangarm.backend.domain.viewingHistory.entity.ViewingHistoryId;

import java.util.Optional;

public interface ViewingHistoryRepository extends JpaRepository<ViewingHistory, ViewingHistoryId> {
    Optional<ViewingHistory> findById_MemberAndId_Precedent(Member member, Precedent precedent);
}
