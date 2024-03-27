package site.pangarm.backend.application.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import site.pangarm.backend.domain.caseType.CaseType;
import site.pangarm.backend.domain.caseType.CaseTypeService;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class BackendApplicationRunner implements ApplicationRunner {
    private final InitialFacade initialFacade;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initialFacade.saveCaseTypeList();
    }

}
