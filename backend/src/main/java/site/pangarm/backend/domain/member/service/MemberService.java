package site.pangarm.backend.domain.member.service;

import site.pangarm.backend.domain.member.dto.MemberJoinDto;

public interface MemberService {
    void signup(MemberJoinDto memberJoinDto);
}
