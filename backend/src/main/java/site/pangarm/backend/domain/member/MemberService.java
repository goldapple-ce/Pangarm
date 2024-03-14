package site.pangarm.backend.domain.member;

import site.pangarm.backend.application.member.dto.request.MemberSignUpRequest;

public interface MemberService {
    void signup(MemberSignUpRequest memberJoinDto);

    Member findByEmail(String email);
}
