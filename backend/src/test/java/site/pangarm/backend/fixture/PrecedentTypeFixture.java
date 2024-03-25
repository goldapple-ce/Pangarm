package site.pangarm.backend.fixture;

import site.pangarm.backend.domain.caseType.entity.CaseType;
import site.pangarm.backend.domain.precedentType.entity.PrecedentType;

public enum PrecedentTypeFixture {
    PRECEDENT_TYPE_FIXTURE("대법원","선고")
    ;

    private final String courtName;

    private final String verdict;

    PrecedentTypeFixture(String courtName, String verdict) {
        this.courtName = courtName;
        this.verdict = verdict;
    }

    public PrecedentType create(){
        return PrecedentType.of(CaseTypeFixture.CASE_TYPE_1_FIXTURE.create(), courtName,verdict);
    }

    public PrecedentType create(CaseType caseType){
        return PrecedentType.of(caseType, courtName,verdict);
    }
}
