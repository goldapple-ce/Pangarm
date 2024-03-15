package site.pangarm.backend.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class MemberDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("MemberDetailService -> loadUserByUsername");
        //password는 스크링 시큐리티가 알아서 처리
        Member member = memberRepository.findByEmail(username);
        if (member == null) {
            throw new UsernameNotFoundException(username);
        }
        //UserDetails에 담아서 return 하면 AuthenticationManager가 검증함
        UserDetails userDetails = new User(
                member.getEmail(),
                member.getPassword(),
                new ArrayList<>()
        );
        System.out.println(userDetails.getUsername() + " " + userDetails.getPassword() + " " + userDetails.getAuthorities());
        return userDetails;
    }

}
