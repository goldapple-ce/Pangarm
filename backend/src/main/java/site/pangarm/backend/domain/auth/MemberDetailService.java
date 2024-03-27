package site.pangarm.backend.domain.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import site.pangarm.backend.domain.member.Member;
import site.pangarm.backend.domain.member.MemberService;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class MemberDetailService implements UserDetailsService {

    private final MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String email){
        Member member = memberService.findByEmail(email);
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getRole().getValue());

        //UserDetails에 담아서 return 하면 AuthenticationManager가 검증함
        return new User(
                String.valueOf(member.getId()),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }

}
