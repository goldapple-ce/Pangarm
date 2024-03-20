package site.pangarm.backend.domain.member;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

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
    private String job;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private Member(String email,String password, String name,int gender,String job,Role role){
        this.email = email;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.job = job;
        this.role = role;
    }

    public static Member of(String email,String password, String name,int gender,String job){
        return new Member(email,password,name,gender,job,Role.USER);
    }
}
