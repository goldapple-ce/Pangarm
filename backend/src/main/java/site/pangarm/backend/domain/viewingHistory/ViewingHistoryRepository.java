package site.pangarm.backend.domain.viewingHistory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.viewingHistory.entity.ViewingHistory;

import java.util.Optional;

public interface ViewingHistoryRepository extends JpaRepository<ViewingHistory, Integer> {
    Optional<ViewingHistory> findByMemberAndPrecedent(Member member, Precedent precedent);

    @Query("select vh,(pb is not null) as isBookmarked " +
            "from ViewingHistory as vh " +
            "left join PrecedentBookmark as pb on pb.precedent = vh.precedent " +
            "where vh.member = :member")
    Page<Object[]> findByMember(Member member, Pageable pageable);
}
