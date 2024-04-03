package site.pangarm.backend.application.runner;

import com.fasterxml.jackson.annotation.JsonProperty;
import site.pangarm.backend.domain.caseType.entity.CaseType;
import site.pangarm.backend.domain.category.entity.Category;

import java.util.List;


record CategoryListVo(List<Category> categoryList) {
    CategoryListVo(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
