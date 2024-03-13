package site.pangarm.backend.domain.member.dto;

import lombok.Getter;
import site.pangarm.backend.domain.member.Member;

@Getter
public class MemberJoinDto {

    private String email;

    private String password;

    private String name;

    private Integer gender;

    private String job;

    public Member toMemberEntity(){
        return Member.builder()
                .email(email)
                .name(name)
                .gender(gender)
                .job(job)
                .build();
    }
}
