package site.pangarm.backend.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import site.pangarm.backend.domain.member.Member;

@Getter
@AllArgsConstructor
public class MemberSignUpRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotNull
    private int gender; // 1 = 남성, 2 = 여성, 3 = 기타

    @NotNull
    private int age;

    @NotBlank
    private String job;

    public Member toEntity(BCryptPasswordEncoder encoder){
        return Member.of(this.email,encoder.encode(this.password),this.name,this.gender,this.age, this.job);
    }
}
