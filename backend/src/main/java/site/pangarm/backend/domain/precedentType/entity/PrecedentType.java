package site.pangarm.backend.domain.precedentType.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.pangarm.backend.domain.caseType.entity.CaseType;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class PrecedentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private CaseType caseType;

    @Column(nullable = false)
    private String courtName;

    @Column(nullable = false)
    private String verdict;

    private PrecedentType(CaseType caseType, String courtName, String verdict){
        this.caseType = caseType;
        this.courtName = courtName;
        this.verdict = verdict;
    }

    public static PrecedentType of(CaseType caseType, String courtName, String verdict){
        return new PrecedentType(caseType,courtName,verdict);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrecedentType that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
