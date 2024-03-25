package site.pangarm.backend.fixture;

import site.pangarm.backend.domain.caseType.entity.CaseType;

public enum CaseTypeFixture {
    CASE_TYPE_1_FIXTURE(1,"가단","민사","민사1심단독사건"),
    CASE_TYPE_2_FIXTURE(2,"가합","민사","민사1심합의사건")
    ;
    private final int code;
    private final String name;
    private final String classification;
    private final String content;

    CaseTypeFixture(int code, String name, String classification, String content) {
        this.code = code;
        this.name = name;
        this.classification = classification;
        this.content = content;
    }

    public CaseType create(){
        return CaseType.of(code,name,classification,content);
    }
}
