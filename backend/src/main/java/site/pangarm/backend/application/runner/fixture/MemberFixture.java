package site.pangarm.backend.application.runner.fixture;


import site.pangarm.backend.domain.member.entity.Member;

public enum MemberFixture {
    MEMBER_FIXTURE("test@example.com","password","tester",1,18,"무직");

    private String email;
    private String password;
    private String name;
    private int gender;
    private int age;
    private String job;

    MemberFixture(String email, String password, String name, int gender,int age, String job) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.job = job;
    }

    public Member create(){
        return Member.of(email,password,name,gender,age,job);
    }
}
