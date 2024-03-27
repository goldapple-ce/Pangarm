package site.pangarm.backend.application.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BackendApplicationRunner implements ApplicationRunner {
    private final InitialFacade initialFacade;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initialFacade.saveCaseTypeList();
    }

}
