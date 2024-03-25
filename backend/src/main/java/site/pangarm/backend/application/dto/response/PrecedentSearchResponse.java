package site.pangarm.backend.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import site.pangarm.backend.domain.searchHistoryPrecedent.entity.SearchHistoryPrecedent;
import site.pangarm.backend.domain.searchHistoryPrecedentKeyword.entity.PrecedentKeyword;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class PrecedentSearchResponse {
    private Page<Precedent> precedentList;
    private List<String> keywordList;

    private PrecedentSearchResponse(Page<Precedent> precedentList,List<String> keywordList) {
        this.precedentList = precedentList;
        this.keywordList = keywordList;
    }

    public static PrecedentSearchResponse of(Page<Object[] > searchHistoryPrecedentPage, List<String> keywordList){
        List<Precedent> precedentList = new ArrayList<>();
        for(Object[] objectList : searchHistoryPrecedentPage){
            SearchHistoryPrecedent searchHistoryPrecedent = (SearchHistoryPrecedent) objectList[0];
            boolean isViewed = (Boolean) objectList[1];
            precedentList.add(Precedent.of(searchHistoryPrecedent,isViewed));
        }
        return new PrecedentSearchResponse(new PageImpl<>(precedentList),keywordList);
    }

    @Getter
    public static class Precedent{
        private final String caseName;
        private final String summary;
        private final boolean isViewed;
        private final double similarity;
        private final List<String> keywords;

        private Precedent(String caseName, String summary, boolean isViewed, double similarity, List<String> keywords) {
            this.caseName = caseName;
            this.summary = summary;
            this.isViewed = isViewed;
            this.similarity = similarity;
            this.keywords = keywords;
        }

        public static Precedent of(SearchHistoryPrecedent searchHistoryPrecedent, boolean isViewed){
            String title = searchHistoryPrecedent.getPrecedent().getCaseName();
            String content = searchHistoryPrecedent.getPrecedent().getSummary();
            double similarity = searchHistoryPrecedent.getScore();

            List<String> keywords = new ArrayList<>();
            for(PrecedentKeyword precedentKeyword : searchHistoryPrecedent.getPrecedent().getPrecedentKeywordList()){
                keywords.add(precedentKeyword.getKeyword());
            }

            return new Precedent(title,content,isViewed,similarity,keywords);
        }
    }

}
