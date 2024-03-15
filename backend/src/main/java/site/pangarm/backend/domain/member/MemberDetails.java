package site.pangarm.backend.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class MemberDetails implements UserDetails {

    private final Member member;

    public MemberDetails(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        log.info("MemberDetails -> getAuthorities");
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        member.getRoleList().forEach(role -> {
            authorities.add(() -> role);
        });
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
        return member.getEmail();
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
