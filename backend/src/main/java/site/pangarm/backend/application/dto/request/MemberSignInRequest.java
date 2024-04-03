package site.pangarm.backend.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberSignInRequest {
    @NotBlank
    @Schema(description = "이메일",defaultValue = "test@example.com")
    private String email;

    @NotBlank
    @Schema(description = "비밀번호",defaultValue = "test")
    private String password;

}
