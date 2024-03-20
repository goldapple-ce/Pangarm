package site.pangarm.backend.domain.member;

import site.pangarm.backend.application.dto.request.MemberSignUpRequest;

public interface MemberService {

    Member save(Member member);

    Member findByEmail(String email);

    Member findById(int userId);
}
