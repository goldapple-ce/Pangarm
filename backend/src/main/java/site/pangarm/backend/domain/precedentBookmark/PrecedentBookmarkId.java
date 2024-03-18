package site.pangarm.backend.domain.precedentBookmark;

import jakarta.persistence.*;
import lombok.*;
import site.pangarm.backend.domain.member.Member;
import site.pangarm.backend.domain.precedent.Precedent;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
@Getter
public class PrecedentBookmarkId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memeber",nullable = false)
    public Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "precedent",nullable = false)
    public Precedent precedent;

}
