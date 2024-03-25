package site.pangarm.backend.domain.viewingHistory.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.precedent.entity.Precedent;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
@Getter
public class ViewingHistoryId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memeber",nullable = false)
    public Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "precedent",nullable = false)
    public Precedent precedent;

}
