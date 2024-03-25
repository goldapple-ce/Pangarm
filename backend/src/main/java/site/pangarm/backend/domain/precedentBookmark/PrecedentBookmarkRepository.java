package site.pangarm.backend.domain.precedentBookmark;

import org.springframework.data.jpa.repository.JpaRepository;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.precedentBookmark.entity.PrecedentBookmark;

import java.util.Optional;

public interface PrecedentBookmarkRepository extends JpaRepository<PrecedentBookmark, Integer> {
    boolean existsByMemberAndPrecedent(Member member, Precedent precedent);
}
