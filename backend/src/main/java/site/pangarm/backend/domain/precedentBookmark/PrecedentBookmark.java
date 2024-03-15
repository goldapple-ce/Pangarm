package site.pangarm.backend.domain.precedentBookmark;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.pangarm.backend.domain.member.Member;
import site.pangarm.backend.domain.precedent.Precedent;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class PrecedentBookmark {
    @EmbeddedId
    private PrecedentBookmarkId id;

    private PrecedentBookmark(Member member, Precedent precedent){
        this.id = new PrecedentBookmarkId(member,precedent);
    }

    public static PrecedentBookmark of(Member member, Precedent precedent){
        return new PrecedentBookmark(member,precedent);
    }
}
