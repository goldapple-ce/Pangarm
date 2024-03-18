package site.pangarm.backend.application.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import site.pangarm.backend.application.runner.CaseTypeListVo;
import site.pangarm.backend.domain.caseType.CaseType;
import site.pangarm.backend.domain.caseType.CaseTypeService;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InitialFacade {
    private final ObjectMapper objectMapper;
    private final CaseTypeService caseTypeService;

    public void saveCaseTypeList() throws IOException {
        List<CaseType> caseTypeList = loadCaseTypeFile();
        caseTypeService.saveAll(caseTypeList);
    }

    private List<CaseType> loadCaseTypeFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("caseTypeList.json");
        CaseTypeListVo wrapper = objectMapper.readValue(resource.getInputStream(), CaseTypeListVo.class);
        return wrapper.caseTypeList();
    }
}
