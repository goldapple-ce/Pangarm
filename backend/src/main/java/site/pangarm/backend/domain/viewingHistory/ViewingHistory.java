package site.pangarm.backend.domain.viewingHistory;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.pangarm.backend.domain.member.Member;
import site.pangarm.backend.domain.precedent.Precedent;
import site.pangarm.backend.domain.precedentBookmark.PrecedentBookmarkId;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ViewingHistory {
    @EmbeddedId
    private ViewingHistoryId id;

    private ViewingHistory(Member member, Precedent precedent){
        this.id = new ViewingHistoryId(member,precedent);
    }

    public static ViewingHistory of(Member member, Precedent precedent){
        return new ViewingHistory(member,precedent);
    }
}
