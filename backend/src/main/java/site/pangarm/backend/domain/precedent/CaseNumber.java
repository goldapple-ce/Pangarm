package site.pangarm.backend.domain.precedent;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import site.pangarm.backend.domain.caseType.CaseType;
import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.exception.BusinessException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EqualsAndHashCode
@NoArgsConstructor
@Embeddable
public class CaseNumber {
    private int caseYear;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "caseType")
    private CaseType caseType;

    private int registrationNumber;

    @Override
    public String toString(){
        return caseYear+caseType.getName()+registrationNumber;
    }

    private CaseNumber(int caseYear, CaseType caseType, int registrationNumber){
        this.caseYear = caseYear;
        this.caseType = caseType;
        this.registrationNumber = registrationNumber;
    }

    public static CaseNumber of(int caseYear, CaseType caseType, int registrationNumber){
        return new CaseNumber(caseYear,caseType,registrationNumber);

    }
}
