package site.pangarm.backend.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.global.error.ErrorCode;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Member save(Member member) {
        validation(member.getEmail());
        return memberRepository.save(member);
    }

    @Override
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(()->
                new MemberException(ErrorCode.API_ERROR_NOT_FOUND));
    }

    @Override
    public Member findById(int id) {
        return memberRepository.findById(id).orElseThrow(()->
                new MemberException(ErrorCode.API_ERROR_NOT_FOUND));
    }

    private void validation(String email){
        if(memberRepository.existsByEmail(email)){
            throw new MemberException(ErrorCode.API_ERROR_ALREADY_EXIST);
        }
    }

}
