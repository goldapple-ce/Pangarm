package site.pangarm.backend.domain.member;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import site.pangarm.backend.domain.member.Member;

interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findByEmail(String email);
}
