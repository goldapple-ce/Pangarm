package site.pangarm.backend.domain.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.member.Member;
import site.pangarm.backend.application.member.dto.request.MemberSignUpRequest;
import site.pangarm.backend.domain.member.MemberService;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
@ActiveProfiles("test")
class MemberServiceImplTest {

    @Autowired
    private MemberService memberService;

    @Nested
    @DisplayName("회원가입 테스트")
    class SignupTest {
        @Test
        @DisplayName("성공")
        void whenSuccess() {
            MemberSignUpRequest memberJoinDto =
                    new MemberSignUpRequest("testemail@gmail.com",
                            "testpassword",
                            "Jane",
                            1,
                            "학생");

            memberService.signup(memberJoinDto);

            Member findMember = memberService.findMemberByEmail("testemail@gmail.com");

            assertEquals("Jane", findMember.getName());
        }
    }


}