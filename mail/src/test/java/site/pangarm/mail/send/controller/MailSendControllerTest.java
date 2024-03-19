package site.pangarm.mail.send.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import site.pangarm.mail.send.service.MailSendService;
import site.pangarm.mail.send.dto.response.MailSendResponse;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MailSendController.class)
class MailSendControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;


    @MockBean
    private MailSendService mailSendService;


    @Nested
    @DisplayName("sendMailController 테스트")
    class sendMailTest {

        @DisplayName("성공")
        @Test
        void whenSuccess() throws Exception {

            Map<String, String> requestData = new HashMap<>();
            requestData.put("email", "apple@naver.com");
            requestData.put("title", "title");
            requestData.put("body", "body");
            String body = mapper.writeValueAsString(
                    requestData
            );

            given(mailSendService.send(any())).willReturn(MailSendResponse.success());
            //then
            mvc.perform(post("/mail/send")
                            .content(body) //HTTP Body에 데이터를 담는다
                            .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
                    )
                    .andExpect(status().isOk());
            //.andExpect(content().string("1"));
        }


        @DisplayName("실패, 이메일 정규식에 일치하지 않음")
        @Test
        void whenFailByEmailNotValid() throws Exception {
            Map<String, String> requestData = new HashMap<>();
            requestData.put("email", "error");
            requestData.put("title", "title");
            requestData.put("body", "body");
            String body = mapper.writeValueAsString(
                    requestData
            );
            given(mailSendService.send(any())).willReturn(MailSendResponse.success());
            //then
            mvc.perform(post("/mail/send")
                            .content(body) //HTTP Body에 데이터를 담는다
                            .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
                    )
                    .andExpect(status().isBadRequest());
        }

        @DisplayName("실패, title이 null임")
        @Test
        void whenFailTitleIsNULL() throws Exception {
            Map<String, String> requestData = new HashMap<>();
            requestData.put("email", "apple@naver.com");
            requestData.put("body", "body");
            String body = mapper.writeValueAsString(
                    requestData
            );
            given(mailSendService.send(any())).willReturn(MailSendResponse.success());
            //then
            mvc.perform(post("/mail/send")
                            .content(body) //HTTP Body에 데이터를 담는다
                            .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
                    )
                    .andExpect(status().isBadRequest());
        }

        @DisplayName("실패, body가 null임")
        @Test
        void whenFailBodyIsNULL() throws Exception {
            Map<String, String> requestData = new HashMap<>();
            requestData.put("email", "apple@naver.com");
            requestData.put("title", "title");
            String body = mapper.writeValueAsString(
                    requestData
            );
            given(mailSendService.send(any())).willReturn(MailSendResponse.success());
            //then
            mvc.perform(post("/mail/send")
                            .content(body) //HTTP Body에 데이터를 담는다
                            .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
                    )
                    .andExpect(status().isBadRequest());
        }

    }
}