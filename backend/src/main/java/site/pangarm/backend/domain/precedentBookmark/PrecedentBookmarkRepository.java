package site.pangarm.backend.domain.precedentBookmark;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.precedentBookmark.entity.PrecedentBookmark;

import java.util.List;
import java.util.Optional;

public interface PrecedentBookmarkRepository extends JpaRepository<PrecedentBookmark, Integer> {
    boolean existsByMemberAndPrecedent(Member member, Precedent precedent);

    Optional<PrecedentBookmark> findByMemberAndPrecedent(Member member, Precedent precedent);

    @Query("select pb,(vh is not null) as isViewed " +
            "from PrecedentBookmark as pb " +
            "left join ViewingHistory as vh on vh.id.precedent = pb.precedent " +
            "where pb.member = :member")
    Page<Object[]> findAllByMember(Member member, Pageable pageable);
}
