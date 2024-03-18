package site.pangarm.backend.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.member.dto.request.MemberLoginRequest;
import site.pangarm.backend.application.member.dto.request.MemberSignUpRequest;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Transactional
    @Override
    public void signup(MemberSignUpRequest memberDto) {
        Member foundMember= findMemberByEmail(memberDto.getEmail());
        if(foundMember != null) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
        memberDto.setEncodedPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
        Member joinMember = memberDto.toMemberEntity();
        joinMember.setRole("ROLE_USER");
        memberRepository.save(joinMember);
    }

    @Override
    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

}
