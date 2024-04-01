package site.pangarm.backend.application.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.pangarm.backend.domain.searchHistoryPrecedent.SearchHistoryOption;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PrecedentSearchRequest {
    @Schema(description = "상황설명",defaultValue = "제가 차에 치였어요.")
    @NotBlank
    private String situation;

    @Schema(description = "키워드")
    private List<String> keywordList;

    @Schema(description = "기간")
    private Duration duration;

    @Schema(description = "제외하기")
    private Preclude preclude;

    @Schema(description = "최소 유사도")
    private int minimumSimilarity;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class Duration{

        @Schema(description = "시작날짜")
        private LocalDate startDate;

        @Schema(description = "끝날짜")
        private LocalDate endDate;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    static class Preclude{
        @Schema(description = "이미 본 판례 제외",type = "Boolean",defaultValue = "false",allowableValues = {"true"})
        private boolean isViewed;

        @Schema(description = "북마크 한 판례 제외",type = "Boolean",defaultValue = "false",allowableValues = {"true"})
        private boolean isBookmarked;
    }

    public SearchHistoryOption toOption(){
        return SearchHistoryOption.builder()
                .keywordList(keywordList)
                .startDate(duration.startDate)
                .endDate(duration.endDate)
                .isBookmarked(preclude.isBookmarked)
                .isViewed(preclude.isViewed)
                .minimumSimilarity(minimumSimilarity)
                .build();
    }
}
