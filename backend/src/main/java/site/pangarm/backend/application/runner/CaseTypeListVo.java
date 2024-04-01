package site.pangarm.backend.application.runner;

import com.fasterxml.jackson.annotation.JsonProperty;
import site.pangarm.backend.domain.caseType.entity.CaseType;

import java.util.List;


record CaseTypeListVo(List<CaseType> caseTypeList) {
    CaseTypeListVo(List<CaseType> caseTypeList) {
        this.caseTypeList = caseTypeList;
    }
}
