package site.pangarm.backend.domain.searchHistoryPrecedent;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;
import site.pangarm.backend.domain.searchHistoryPrecedent.entity.SearchHistoryPrecedent;

interface SearchHistoryPrecedentRepository extends JpaRepository<SearchHistoryPrecedent,Integer> {
    boolean existsBySearchHistoryAndPrecedent(SearchHistory searchHistory, Precedent precedent);

    @Query("select shp, (vh is not null) as isViewed " +
            "from SearchHistoryPrecedent as shp " +
            "left join ViewingHistory as vh on vh.id.precedent = shp.precedent " +
            "where shp.searchHistory = :searchHistory " +
            "order by shp.score desc")
    Page<Object[]> findAllWithIsViewedBySearchHistoryOrderByScore(SearchHistory searchHistory, Pageable pageable);

}
