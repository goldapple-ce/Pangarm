package site.pangarm.backend.application.runner;

import com.fasterxml.jackson.annotation.JsonProperty;
import site.pangarm.backend.domain.caseType.CaseType;

import java.util.List;


record CaseTypeListVo(@JsonProperty List<CaseType> caseTypeList) {
    CaseTypeListVo(@JsonProperty List<CaseType> caseTypeList) {
        this.caseTypeList = caseTypeList;
    }
}
