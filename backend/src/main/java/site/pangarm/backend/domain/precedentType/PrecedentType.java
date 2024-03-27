package site.pangarm.backend.domain.precedentType;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.pangarm.backend.domain.precedent.Precedent;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class PrecedentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String incident;

    @Column(nullable = false)
    private String courtName;

    @Column(nullable = false)
    private int instance;

    private PrecedentType(String incident, String courtName, int instance){
        this.incident = incident;
        this.courtName = courtName;
        this.instance = instance;
    }

    public static PrecedentType of(String incident, String courtName, int instance){
        return new PrecedentType(incident,courtName,instance);
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
