package site.pangarm.backend.domain.member;

import site.pangarm.backend.domain.member.entity.Member;

public interface MemberService {

    Member save(Member member);

    Member findByEmail(String email);

    Member findById(int userId);
}
