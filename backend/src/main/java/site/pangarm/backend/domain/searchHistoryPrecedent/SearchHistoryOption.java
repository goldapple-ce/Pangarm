package site.pangarm.backend.domain.searchHistoryPrecedent;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SearchHistoryOption {
    private List<String> keywordList;
    private Duration duration;
    private Preclude preclude;
    private int minimumSimilarity;

    @Builder
    public SearchHistoryOption(List<String> keywordList, LocalDate startDate, LocalDate endDate, boolean isViewed, boolean isBookmarked,int minimumSimilarity){
        this.keywordList = keywordList;
        this.duration = new Duration(startDate,endDate);
        this.preclude = new Preclude(isViewed,isBookmarked);
        this.minimumSimilarity = minimumSimilarity;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    static class Duration{
        private LocalDate startDate;
        private LocalDate endDate;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    static class Preclude{
        private boolean isViewed;
        private boolean isBookmarked;
    }
}
