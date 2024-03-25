package site.pangarm.backend.domain.searchHistoryPrecedentKeyword;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;
import site.pangarm.backend.domain.searchHistoryPrecedentKeyword.entity.PrecedentKeyword;

import java.util.List;

interface PrecedentKeywordRepository extends JpaRepository<PrecedentKeyword,Integer> {

    @Query("select pk.keyword " +
            "from PrecedentKeyword as pk " +
            "join SearchHistoryPrecedent shp " +
                "on pk.precedent = shp.precedent " +
            "where shp.searchHistory = :searchHistory " +
            "group by pk.keyword " +
            "order by COUNT(pk.id) desc limit 10")
    List<String> findAllKeywordBySearchHistoryPrecedent_SearchHistory(SearchHistory searchHistory);
}
