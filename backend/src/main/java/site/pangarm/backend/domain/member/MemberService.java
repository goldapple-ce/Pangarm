package site.pangarm.backend.domain.member;

import org.springframework.security.core.userdetails.User;
import site.pangarm.backend.domain.member.entity.Member;
import java.util.List;

public interface MemberService {

    Member save(Member member);

    Member findByEmail(String email);

    Member findById(int userId);
    Member findByUser(User user);
    List<Member> findAll();
}
