package site.pangarm.backend.domain.searchHistory.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.searchHistoryPrecedent.entity.SearchHistoryPrecedent;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String situation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    @OneToMany(mappedBy = "searchHistory")
    private final List<SearchHistoryPrecedent> searchHistoryPrecedentList = new ArrayList<>();

    private SearchHistory(Member member, String situation){
        this.member = member;
        this.situation = situation;
    }

    public static SearchHistory of(String situation,Member member){
        return new SearchHistory(member,situation);
    }
}
