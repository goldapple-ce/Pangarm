package site.pangarm.backend.domain.caseType.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class  CaseType {
    @Id
    private Integer code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String classification;

    @Column(nullable = false)
    private String content;

    private CaseType(Integer code, String name, String classification, String content){
        this.code = code;
        this.name = name;
        this.classification = classification;
        this.content = content;
    }

    public static CaseType of(Integer code, String name, String classification, String content){
        return new CaseType(code,name,classification,content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaseType caseType)) return false;
        return Objects.equals(code, caseType.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
