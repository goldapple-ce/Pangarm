package site.pangarm.backend.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import site.pangarm.backend.domain.member.entity.Member;

@Getter
@AllArgsConstructor
public class MemberSignUpRequest {

    @NotBlank
    @Schema(description = "이메일",defaultValue = "test@example.com")
    private String email;

    @NotBlank
    @Schema(description = "비밀번호",defaultValue = "test")
    private String password;

    @NotBlank
    @Schema(description = "이름",defaultValue = "tester")
    private String name;

    @NotNull
    @Schema(description = "성별(1:남성,2:여성,3:기타)",type = "Integer",defaultValue = "1", allowableValues = {"2","3"})
    private int gender; // 1 = 남성, 2 = 여성, 3 = 기타

    @NotNull
    @Schema(description = "나이",type = "Integer",defaultValue = "25")
    private int age;

    @NotBlank
    @Schema(description = "직업",defaultValue = "none")
    private String job;

    public Member toEntity(BCryptPasswordEncoder encoder){
        return Member.of(this.email,encoder.encode(this.password),this.name,this.gender,this.age, this.job);
    }
}
