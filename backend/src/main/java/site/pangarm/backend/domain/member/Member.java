package site.pangarm.backend.domain.member;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.hibernate.annotations.CollectionId;
import org.springframework.lang.NonNull;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @org.springframework.lang.NonNull
    @Column(nullable = false)
    private String email;

    @lombok.NonNull
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer gender;

    @Column(nullable = false)
    private String job;

}
