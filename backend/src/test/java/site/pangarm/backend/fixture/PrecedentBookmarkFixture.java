package site.pangarm.backend.fixture;

import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.precedentBookmark.entity.PrecedentBookmark;

public enum PrecedentBookmarkFixture {
    PRECEDENT_BOOKMARK_FIXTURE;
    public PrecedentBookmark create(){
        return PrecedentBookmark.of(MemberFixture.MEMBER_FIXTURE.create(), PrecedentFixture.PRECEDENT_1_FIXTURE.create());
    }

    public PrecedentBookmark create(Member member, Precedent precedent){
        return PrecedentBookmark.of(member, precedent);
    }
}
