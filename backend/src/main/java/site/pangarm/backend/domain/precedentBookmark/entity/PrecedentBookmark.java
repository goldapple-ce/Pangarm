package site.pangarm.backend.domain.precedentBookmark.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.global.entity.BaseEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"member_id","precedent_id"}))
@Entity
public class PrecedentBookmark extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    public Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "precedent_id",nullable = false)
    public Precedent precedent;

    private PrecedentBookmark(Member member, Precedent precedent){
        this.member = member;
        this.precedent = precedent;
    }

    public static PrecedentBookmark of(Member member, Precedent precedent){
        return new PrecedentBookmark(member,precedent);
    }
}
