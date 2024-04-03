package site.pangarm.backend.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import site.pangarm.backend.domain.member.entity.Member;

import java.util.Optional;

interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);
}
