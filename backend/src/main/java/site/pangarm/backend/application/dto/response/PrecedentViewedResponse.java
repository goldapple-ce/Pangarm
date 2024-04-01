package site.pangarm.backend.application.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import site.pangarm.backend.domain.precedentBookmark.entity.PrecedentBookmark;
import site.pangarm.backend.domain.viewingHistory.entity.ViewingHistory;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PrecedentViewedResponse {
    List<PrecedentResponse> precedentList;

    public static PrecedentViewedResponse of (Page<Object[] > viewedPrecedentPage){
        List<PrecedentResponse> precedentList = new ArrayList<>();
        for(Object[] objectList : viewedPrecedentPage){
            ViewingHistory viewingHistory = (ViewingHistory) objectList[0];
            boolean isBookmarked = (boolean) objectList[1];
            precedentList.add(PrecedentResponse.of(viewingHistory,true,isBookmarked));
        }
        return new PrecedentViewedResponse(precedentList);
    }

}
