package site.pangarm.backend.application.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("init")
@RequiredArgsConstructor
public class BackendApplicationRunner implements ApplicationRunner {

    private final InitialFacade initialFacade;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initialFacade.saveCaseTypeList();
        initialFacade.saveNewsCategory();
    }
}
