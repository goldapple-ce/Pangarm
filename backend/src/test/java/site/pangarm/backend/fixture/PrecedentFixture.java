package site.pangarm.backend.fixture;

import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.precedentType.entity.PrecedentType;

import java.time.LocalDate;

public enum PrecedentFixture {
    PRECEDENT_1_FIXTURE("1234가1234","힘든판례1","요약1"),
    PRECEDENT_2_FIXTURE("5678나1234","힘든판례2","요약2"),
    PRECEDENT_3_FIXTURE("1234다5678","힘든판례3","요약3"),
    PRECEDENT_4_FIXTURE("5678라1234","힘든판례4","요약4"),
    PRECEDENT_5_FIXTURE("5678마1234","힘든판례5","요약5"),
    PRECEDENT_6_FIXTURE("5678바1234","힘든판례6","요약6"),
    PRECEDENT_7_FIXTURE("5678사1234","힘든판례7","요약7"),
    PRECEDENT_8_FIXTURE("5678아1234","힘든판례8","요약8"),
    PRECEDENT_9_FIXTURE("5678자1234","힘든판례9","요약9"),
    PRECEDENT_10_FIXTURE("5675자1234","힘든판례10","요약10"),

    ;
    private final String caseNumber;
    private final String caseName;

    private final String summary;
    PrecedentFixture(String caseNumber, String caseName,String summary) {
        this.caseNumber = caseNumber;
        this.caseName = caseName;
        this.summary = summary;
    }

    public Precedent create(){
        return Precedent.of(caseNumber,caseName,LocalDate.now(),summary,PrecedentTypeFixture.PRECEDENT_TYPE_FIXTURE.create());
    }

    public Precedent create(PrecedentType precedentType){
        return Precedent.of(caseNumber,caseName,LocalDate.now(),summary,precedentType);
    }
}
