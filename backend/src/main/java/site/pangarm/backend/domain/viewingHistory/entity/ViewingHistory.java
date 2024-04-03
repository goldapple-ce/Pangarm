package site.pangarm.backend.domain.viewingHistory.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.global.entity.BaseEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"member_id", "precedent_id"})
})
public class ViewingHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "precedent_id", nullable = false)
    private Precedent precedent;


    private ViewingHistory(Member member, Precedent precedent) {
        this.member = member;
        this.precedent = precedent;
    }

    public static ViewingHistory of(Member member, Precedent precedent) {
        return new ViewingHistory(member, precedent);
    }
}
