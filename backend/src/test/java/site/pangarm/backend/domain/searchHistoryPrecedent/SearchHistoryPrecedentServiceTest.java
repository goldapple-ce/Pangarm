package site.pangarm.backend.domain.searchHistoryPrecedent;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class SearchHistoryPrecedentServiceTest {

    @Autowired
    private SearchHistoryPrecedentService searchHistoryPrecedentService;

    @DisplayName("save 테스트")
    @Nested
    class saveTest{

        @Test
        void save() {
        }
    }

    @Test
    void findAllBySearchHistory() {
    }
}