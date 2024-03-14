package site.pangarm.backend.application.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import site.pangarm.backend.domain.member.Member;

@Getter
@AllArgsConstructor
public class MemberSignUpRequest {

    private String email;

    private String password;

    private String name;

    private Integer gender; // 1 = 남성, 2 = 여성, 3 = 기타

    private String job;

    public void setEncodedPassword(String password) {
        this.password = password;
    }

    public Member toMemberEntity(){
        return Member.builder()
                .email(email)
                .name(name)
                .gender(gender)
                .job(job)
                .password(password)
                .build();
    }
}
