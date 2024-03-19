package site.pangarm.mail.send.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import site.pangarm.mail.send.dto.request.MailSendRequest;
import site.pangarm.mail.send.dto.response.MailSendResponse;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MailServiceImplTest {

    @Autowired
    private MailServiceImpl mailService;

    private MailSendRequest request;


    @BeforeEach
    void beforeEach() {
        request = new MailSendRequest("test@apple.com", "안녕하세요", "<h1>자랑스럽다!! </h1>");
    }

    @Nested
    @DisplayName("sendMail 테스트")
    class sendMailTest {

        @DisplayName("성공")
        @Test
        void whenSuccess() throws Exception {
            MailSendResponse response = mailService.send(request);
            MailSendResponse successResponse = MailSendResponse.success();
            assertThat(response.getMessage()).isEqualTo(successResponse.getMessage());
        }

    }

}