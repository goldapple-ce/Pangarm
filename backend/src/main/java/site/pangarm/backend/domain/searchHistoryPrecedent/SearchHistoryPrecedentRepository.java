package site.pangarm.backend.domain.searchHistoryPrecedent;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;
import site.pangarm.backend.domain.searchHistoryPrecedent.entity.SearchHistoryPrecedent;

import java.util.Optional;

interface SearchHistoryPrecedentRepository extends JpaRepository<SearchHistoryPrecedent,Integer> {
    boolean existsBySearchHistoryAndPrecedent(SearchHistory searchHistory, Precedent precedent);
    Optional<SearchHistoryPrecedent> findBySearchHistoryAndPrecedent(SearchHistory searchHistory, Precedent precedent);

    @Query(value = "select distinct shp, (vh.id.precedent is not null) ,(pb.precedent is not null)  " +
            "from SearchHistoryPrecedent as shp " +
            "left join ViewingHistory as vh on vh.id.precedent = shp.precedent " +
            "left join PrecedentBookmark as pb on pb.precedent = shp.precedent " +
            "left join PrecedentKeyword as pk on pk.precedent = shp.precedent " +
            "where shp.searchHistory = :searchHistory  " +
            "and ((:#{#option.preclude.isViewed} = false) or vh.id.precedent is not null) " +
            "and ((:#{#option.preclude.isBookmarked} = false) or pb.precedent is not null) " +
            "and shp.score > :#{#option.minimumSimilarity} " +
            "and ((:#{#option.duration.startDate} is null) or (shp.precedent.judgementDate >= :#{#option.duration.startDate})) " +
            "and ((:#{#option.duration.endDate} is null) or (shp.precedent.judgementDate <= :#{#option.duration.endDate})) " +
            "and (pk.keyword in :#{#option.keywordList}) " +
            "order by shp.score desc")
    Page<Object[]> findDistinctByOptionWithKeywordList(SearchHistory searchHistory, SearchHistoryOption option,Pageable pageable);


    @Query(value = "select distinct shp, (vh.id.precedent is not null), (pb.precedent is not null) " +
            "from SearchHistoryPrecedent as shp " +
            "left join ViewingHistory as vh on vh.id.precedent = shp.precedent " +
            "left join PrecedentBookmark as pb on pb.precedent = shp.precedent " +
            "left join PrecedentKeyword as pk on pk.precedent = shp.precedent " +
            "where shp.searchHistory = :searchHistory  " +
            "and ((:#{#option.preclude.isViewed} = false) or vh.id.precedent is not null) " +
            "and ((:#{#option.preclude.isBookmarked} = false) or pb.precedent is not null) " +
            "and shp.score > :#{#option.minimumSimilarity} " +
            "and ((:#{#option.duration.startDate} is null) or (shp.precedent.judgementDate >= :#{#option.duration.startDate})) " +
            "and ((:#{#option.duration.endDate} is null) or (shp.precedent.judgementDate <= :#{#option.duration.endDate})) " +
            "order by shp.score desc")
    Page<Object[]> findDistinctByOption(SearchHistory searchHistory, SearchHistoryOption option,Pageable pageable);
}
