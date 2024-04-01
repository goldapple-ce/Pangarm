package site.pangarm.backend.application.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PrecedentSearchHistoryResponse {
    private List<PrecedentSearch> precedentSearchList;

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Getter
    static class PrecedentSearch{
        private int id;
        private String situation;
    }

    public static PrecedentSearchHistoryResponse of(List<SearchHistory> searchHistoryList){
        List<PrecedentSearch> precedentSearchList = new ArrayList<>();
        for(SearchHistory searchHistory : searchHistoryList){
            int id = searchHistory.getId();
            String situation = searchHistory.getSituation();
            precedentSearchList.add(new PrecedentSearch(id,situation));
        }
        return new PrecedentSearchHistoryResponse(precedentSearchList);
    }
}
