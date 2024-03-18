package site.pangarm.backend.domain.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import site.pangarm.backend.domain.member.Member;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Slf4j
public class MemberDetails implements UserDetails {

    private final Member member;

    public MemberDetails(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(()->member.getRole());
        return authorities;

    }

    @Override
    public String getPassword() {
        log.info("MemberDetails -> getPassword");
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        log.info("MemberDetails -> getUsername");
        return String.valueOf(member.getId());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
