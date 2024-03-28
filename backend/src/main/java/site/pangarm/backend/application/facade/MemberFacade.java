package site.pangarm.backend.application.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.dto.request.MemberSignUpRequest;
import site.pangarm.backend.domain.member.MemberService;
import site.pangarm.backend.domain.member.entity.Member;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberFacade {

    private final MemberService memberService;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void signup(MemberSignUpRequest request){
        memberService.save(request.toEntity(encoder));
    }

    public Member getById(int userId) {
        return memberService.findById(userId);
    }

}
