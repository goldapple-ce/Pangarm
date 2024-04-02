package site.pangarm.backend.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.searchHistoryPrecedent.entity.SearchHistoryPrecedent;
import site.pangarm.backend.domain.searchHistoryPrecedentKeyword.entity.PrecedentKeyword;
import site.pangarm.backend.domain.viewingHistory.entity.ViewingHistory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class PrecedentResponse {
    private final int id;
    private final String caseNumber;
    private final String caseName;
    private final String summary;
    private final boolean isBookmarked;
    private final boolean isViewed;
    private final Double similarity;
    private final List<String> keywordList;
    private final LocalDate createAt;

    private PrecedentResponse(int id, String caseNumber, String caseName, String summary, boolean isBookmarked, boolean isViewed, LocalDate createAt, List<String> keywordList) {
        this(id, caseNumber, caseName, summary, isBookmarked, isViewed, null, keywordList, createAt);
    }

    private PrecedentResponse(int id, String caseNumber, String caseName, String summary, boolean isBookmarked, boolean isViewed, List<String> keywordList) {
        this(id, caseNumber, caseName, summary, isBookmarked, isViewed, null, keywordList, null);
    }

    public PrecedentResponse(int id, String caseNumber, String caseName, String content, boolean isBookmarked, boolean isViewed, double similarity, List<String> keywords) {
        this(id, caseNumber, caseName, content, isBookmarked, isViewed, similarity, keywords, null);
    }

    public static PrecedentResponse of(SearchHistoryPrecedent searchHistoryPrecedent, boolean isViewed, boolean isBookmarked) {
        int id = searchHistoryPrecedent.getPrecedent().getId();
        String caseNumber = searchHistoryPrecedent.getPrecedent().getCaseNumber().toString();
        String title = searchHistoryPrecedent.getPrecedent().getCaseName();
        String content = searchHistoryPrecedent.getPrecedent().getSummary();
        double similarity = searchHistoryPrecedent.getScore();

        List<String> keywords = extractKeywordList(searchHistoryPrecedent.getPrecedent());

        return new PrecedentResponse(id, caseNumber, title, content, isBookmarked, isViewed, similarity, keywords);
    }

    public static PrecedentResponse of(Precedent precedent, boolean isViewed, boolean isBookmarked) {
        int id = precedent.getId();
        String caseNumber = precedent.getCaseNumber().toString();
        String title = precedent.getCaseName();
        String content = precedent.getSummary();
        List<String> keywordList = extractKeywordList(precedent);

        return new PrecedentResponse(id,caseNumber, title, content, isBookmarked, isViewed, keywordList);
    }

    public static PrecedentResponse of(ViewingHistory viewingHistory, boolean isViewed, boolean isBookmarked) {
        Precedent precedent = viewingHistory.getId().getPrecedent();

        int id = precedent.getId();
        String caseNumber = precedent.getCaseNumber().toString();
        String title = precedent.getCaseName();
        String content = precedent.getSummary();
        List<String> keywordList = extractKeywordList(precedent);

        return new PrecedentResponse(id, caseNumber,title, content, isBookmarked, isViewed, precedent.getJudgementDate(), keywordList);
    }

    private static List<String> extractKeywordList(Precedent precedent) {
        List<String> keywordList = new ArrayList<>();
        for (PrecedentKeyword precedentKeyword : precedent.getPrecedentKeywordList()) {
            keywordList.add(precedentKeyword.getKeyword());
        }
        return keywordList;
    }
}
