package site.pangarm.backend.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.member.Member;
import site.pangarm.backend.domain.member.dto.MemberJoinDto;
import site.pangarm.backend.domain.member.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public void signup(MemberJoinDto memberJoinDto) {
        Member joinMember = memberJoinDto.toMemberEntity();
        joinMember.setPassword(bCryptPasswordEncoder.encode(memberJoinDto.getPassword()));
        memberRepository.save(joinMember);
    }

}
