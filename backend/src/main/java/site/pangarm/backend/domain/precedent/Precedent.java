package site.pangarm.backend.domain.precedent;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.pangarm.backend.domain.precedentType.PrecedentType;
import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.exception.BusinessException;

import java.time.LocalDate;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "caseNumber",
                columnNames = {"caseYear","caseType","registrationNumber"}
        )
})
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

    @ManyToOne
    @JoinColumn(nullable = false)
    private PrecedentType precedentType;


    private Precedent(CaseNumber caseNumber, String caseName, LocalDate judgementDate,PrecedentType precedentType){
        this.caseNumber = caseNumber;
        this.caseName = caseName;
        this.judgementDate = judgementDate;
        this.precedentType = precedentType;
    }

    public static Precedent of(CaseNumber caseNumber, String caseName, LocalDate judgementDate, PrecedentType precedentType){
        return new Precedent(caseNumber,caseName,judgementDate,precedentType);
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
