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
    public void signup(MemberSignUpRequest memberJoinDto) {
        memberJoinDto.setEncodedPassword(bCryptPasswordEncoder.encode(memberJoinDto.getPassword()));
        memberRepository.save(memberJoinDto.toMemberEntity());
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

}
