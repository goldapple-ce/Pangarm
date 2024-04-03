package site.pangarm.backend.application.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.precedentBookmark.entity.PrecedentBookmark;
import site.pangarm.backend.domain.searchHistoryPrecedent.entity.SearchHistoryPrecedent;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PrecedentBookmarkedResponse {
    List<PrecedentResponse> precedentList;

    public static PrecedentBookmarkedResponse of (Page<Object[] > precedentBookmarkPage){
        List<PrecedentResponse> precedentList = new ArrayList<>();
        for(Object[] objectList : precedentBookmarkPage){
            PrecedentBookmark precedentBookmark = (PrecedentBookmark) objectList[0];
            boolean isViewed = (boolean) objectList[1];
            precedentList.add(PrecedentResponse.of(precedentBookmark.getPrecedent(),isViewed,true));
        }
        return new PrecedentBookmarkedResponse(precedentList);
    }

}
