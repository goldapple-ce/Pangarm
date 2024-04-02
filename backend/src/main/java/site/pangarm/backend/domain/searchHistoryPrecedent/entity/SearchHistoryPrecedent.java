package site.pangarm.backend.domain.searchHistoryPrecedent.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;
import site.pangarm.backend.global.entity.BaseEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"search_history_id","precedent_id"})})
public class SearchHistoryPrecedent extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "search_history_id",nullable = false)
    private SearchHistory searchHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "precedent_id",nullable = false)
    private Precedent precedent;

    @Column(nullable = false)
    private double score;

    private SearchHistoryPrecedent(SearchHistory searchHistory, Precedent precedent,double score) {
        this.precedent = precedent;
        this.score = score;
        addRelated(searchHistory);
    }

    private void addRelated(SearchHistory searchHistory){
        this.searchHistory = searchHistory;
        searchHistory.getSearchHistoryPrecedentList().add(this);
    }

    public static SearchHistoryPrecedent of(SearchHistory searchHistory, Precedent precedent,double score) {
        return new SearchHistoryPrecedent(searchHistory,precedent,score);
    }
}
