package site.pangarm.backend.domain.precedent.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.pangarm.backend.domain.precedentType.entity.PrecedentType;
import site.pangarm.backend.domain.searchHistoryPrecedentKeyword.entity.PrecedentKeyword;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(uniqueConstraints = @UniqueConstraint(
        columnNames = {"caseYear", "caseTypeName", "registrationNumber"}
))
@Entity
public class Precedent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    @Column(nullable = false)
    private CaseNumber caseNumber;

    @Column(nullable = false)
    private String caseName;

    @Column(nullable = false)
    private LocalDate judgementDate;

    @Column
    private String summary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private PrecedentType precedentType;

    @OneToMany(mappedBy = "precedent")
    private final List<PrecedentKeyword> precedentKeywordList = new ArrayList<>();

    private Precedent(CaseNumber caseNumber, String caseName, LocalDate judgementDate,String summary,PrecedentType precedentType){
        this.caseNumber = caseNumber;
        this.caseName = caseName;
        this.summary = summary;
        this.judgementDate = judgementDate;
        this.precedentType = precedentType;
    }

    public static Precedent of(String caseNumber, String caseName, LocalDate judgementDate, String summary,PrecedentType precedentType){
        if(summary != null && summary.length() > 200){
            summary = summary.substring(0,200);
        }
        return new Precedent(CaseNumber.of(caseNumber),caseName,judgementDate,summary,precedentType);
    }

    public static Precedent of(CaseNumber caseNumber, String caseName, LocalDate judgementDate, String summary,PrecedentType precedentType){
        if(summary != null && summary.length() > 200){
            summary = summary.substring(0,200);
        }
        return new Precedent(caseNumber,caseName,judgementDate,summary,precedentType);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Precedent precedent)) return false;
        return getId() != null && Objects.equals(this.id,precedent.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
