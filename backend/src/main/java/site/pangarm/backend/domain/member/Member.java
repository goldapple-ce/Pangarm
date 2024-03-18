package site.pangarm.backend.domain.member;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
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
    private Integer gender;

    @Column(nullable = false)
    private String job;

    private String role;

    void setRole(String role) {
        this.role = role;
    }
    public List<String> getRoleList(){
        if(!this.role.isEmpty()){
            return List.of(this.role);
        }
        return new ArrayList<>();
    }
}
