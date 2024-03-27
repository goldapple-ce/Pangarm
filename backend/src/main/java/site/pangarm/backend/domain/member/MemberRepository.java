package site.pangarm.backend.domain.member;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import site.pangarm.backend.domain.member.Member;

import java.util.Optional;

interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}
