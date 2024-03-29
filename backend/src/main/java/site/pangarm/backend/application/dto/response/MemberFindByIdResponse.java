package site.pangarm.backend.application.dto.response;

import site.pangarm.backend.domain.member.entity.Member;

public record MemberFindByIdResponse(int id, String email, String password, String name, int gender, int age, String job) {

    public static MemberFindByIdResponse of(Member member){
        return new MemberFindByIdResponse(
                member.getId(), member.getEmail(), member.getPassword(), member.getName(),
                member.getGender(), member.getAge(), member.getJob());
    }

}
