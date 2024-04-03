package site.pangarm.backend.application.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.caseType.CaseTypeService;
import site.pangarm.backend.domain.category.CategoryService;
import java.io.IOException;

@Transactional
@RequiredArgsConstructor
@Service
class InitialFacade {
    private final ObjectMapper objectMapper;
    private final CaseTypeService caseTypeService;
    private final CategoryService categoryService;

    void saveCaseTypeList() throws IOException {
        CaseTypeListVo caseTypeListVo = loadFile("caseTypeList.json", CaseTypeListVo.class);
        caseTypeService.saveAll(caseTypeListVo.caseTypeList());
    }

    void saveNewsCategory() throws IOException {
        CategoryListVo categoryListVo = loadFile("categoryList.json", CategoryListVo.class);
        categoryListVo.categoryList()
                .forEach(categoryService::save);
    }

    protected <T> T loadFile(String path, Class<T> responseType) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return objectMapper.readValue(resource.getInputStream(), responseType);
    }

}
