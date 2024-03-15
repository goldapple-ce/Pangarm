package site.pangarm.backend.domain.viewingHistory;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ViewingHistoryServiceTest {
    @Autowired
    private ViewingHistoryService viewingHistoryService;

    @Nested
    @DisplayName("save 테스트")
    class saveTest{

        @DisplayName("성공")
        @Test
        void whenSuccess() {
        }

        @DisplayName("실패, 이미 존재함")
        @Test
        void whenFailByAlreadyExist() {
        }
    }

    @Nested
    @DisplayName("findById 테스트")
    class findById{

        @DisplayName("성공")
        @Test
        void whenSuccess() {
        }

        @DisplayName("실패, 존재하지 않음")
        @Test
        void whenFailByNotExist() {
        }
    }
}