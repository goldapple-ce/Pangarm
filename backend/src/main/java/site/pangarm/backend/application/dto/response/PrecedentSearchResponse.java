package site.pangarm.backend.application.dto.response;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import site.pangarm.backend.domain.searchHistoryPrecedent.entity.SearchHistoryPrecedent;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PrecedentSearchResponse {
    private final Page<PrecedentResponse> precedentList;
    private final List<String> keywordList;

    public static PrecedentSearchResponse of(Page<Object[] > searchHistoryPrecedentPage, List<String> keywordList){
        List<PrecedentResponse> precedentList = new ArrayList<>();
        for(Object[] objectList : searchHistoryPrecedentPage){
            SearchHistoryPrecedent searchHistoryPrecedent = (SearchHistoryPrecedent) objectList[0];
            boolean isViewed = (boolean) objectList[1];
            boolean isBookmarked = (boolean) objectList[2];
            precedentList.add(PrecedentResponse.of(searchHistoryPrecedent,isViewed,isBookmarked));
        }
        PrecedentSearchResponse response = new PrecedentSearchResponse(new PageImpl<>(precedentList),keywordList);
        return response;
    }

}
