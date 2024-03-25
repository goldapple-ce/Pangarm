package site.pangarm.backend.application.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
public class PrecedentSearchRequest {
    @Schema(description = "상황설명",defaultValue = "제가 차에 치였어요.")
    @NotBlank
    @JsonProperty("content")
    private final String situation;

    @JsonCreator
    public PrecedentSearchRequest(@JsonProperty String situation){
        this.situation = situation;
    }
}
