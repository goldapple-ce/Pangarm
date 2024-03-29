package site.pangarm.backend.application.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.dto.response.PrecedentSearchDetailClientResponse;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class PrecedentFetchAPITest {
    @Autowired
    private PrecedentFetchAPI precedentFetchAPI;

    @Test
    void fetchAPI() {
        assertDoesNotThrow(()->{
            PrecedentSearchDetailClientResponse response = precedentFetchAPI.fetchAPI("/detail?caseNumber=","75다1860", PrecedentSearchDetailClientResponse.class);

            System.out.println(response.getBody());
        });
    }
}