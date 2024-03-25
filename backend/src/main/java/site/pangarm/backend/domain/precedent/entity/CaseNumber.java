package site.pangarm.backend.domain.precedent.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.pangarm.backend.domain.precedent.PrecedentException;
import site.pangarm.backend.global.error.ErrorCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class CaseNumber {
    @Column(nullable = false)
    private int caseYear;
    @Column(nullable = false)
    private String caseTypeName;
    @Column(nullable = false)
    private int registrationNumber;

    private CaseNumber(int caseYear, String caseTypeName, int registrationNumber){
        this.caseYear = caseYear;
        this.caseTypeName = caseTypeName;
        this.registrationNumber = registrationNumber;
    }

    public static CaseNumber of(String caseNumber){
        Pattern pattern = Pattern.compile("(\\d+)(\\D+)(\\d+)");
        Matcher matcher = pattern.matcher(caseNumber);

        if(matcher.find()) {
            int caseYear = Integer.parseInt(matcher.group(1));
            String caseTypeName = matcher.group(2);
            int registrationNumber = Integer.parseInt(matcher.group(3));

            return new CaseNumber(caseYear, caseTypeName, registrationNumber);
        }else throw new PrecedentException(ErrorCode.API_ERROR_INPUT_INVALID_VALUE);
    }

    @Override
    public String toString() {
        return caseYear+caseTypeName+registrationNumber;
    }
}
