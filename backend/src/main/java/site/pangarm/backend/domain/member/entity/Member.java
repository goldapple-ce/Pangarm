package site.pangarm.backend.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import site.pangarm.backend.domain.membercategory.entity.MemberCategory;
import site.pangarm.backend.global.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int gender;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String job;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "member")
    private List<MemberCategory> memberCategoryList = new ArrayList<>();

    private Member(String email,String password, String name,int gender, int age, String job,Role role){
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.job = job;
        this.role = role;
    }

    public static Member of(String email, String password, String name, int gender, int age,  String job){
        return new Member(email,password,name,gender, age, job,Role.USER);
    }

}
