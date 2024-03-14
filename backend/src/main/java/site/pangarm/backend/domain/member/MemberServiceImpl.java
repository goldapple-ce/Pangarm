package site.pangarm.backend.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.member.dto.request.MemberSignUpRequest;

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
        memberRepository.save(memberDto.toMemberEntity());
    }

    @Override
    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

}
