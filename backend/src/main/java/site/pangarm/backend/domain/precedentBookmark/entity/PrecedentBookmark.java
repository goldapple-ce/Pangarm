package site.pangarm.backend.domain.precedentBookmark.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.precedent.entity.Precedent;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"member","precedent"}))
@Entity
public class PrecedentBookmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member",nullable = false)
    public Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "precedent",nullable = false)
    public Precedent precedent;

    private PrecedentBookmark(Member member, Precedent precedent){
        this.member = member;
        this.precedent = precedent;
    }

    public static PrecedentBookmark of(Member member, Precedent precedent){
        return new PrecedentBookmark(member,precedent);
    }
}
