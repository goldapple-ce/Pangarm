package site.pangarm.backend.domain.searchHistoryPrecedentKeyword.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.pangarm.backend.domain.precedent.entity.Precedent;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class PrecedentKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Precedent precedent;

    @Column(nullable = false)
    private String keyword;

    private PrecedentKeyword(Precedent precedent, String keyword) {
        this.keyword = keyword;
        addRelated(precedent);
    }

    private void addRelated(Precedent precedent){
        this.precedent = precedent;
        precedent.getPrecedentKeywordList().add(this);
    }

    public static PrecedentKeyword of(Precedent precedent, String keyword){
        return new PrecedentKeyword(precedent,keyword);
    }
}
