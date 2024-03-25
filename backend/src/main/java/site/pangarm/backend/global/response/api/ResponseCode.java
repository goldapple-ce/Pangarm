package site.pangarm.backend.global.response.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    API_SUCCESS_DOMAIN_METHOD(200,"EXAMPLE001","예상 메시지입니다."),

    //MEMBER
    API_SUCCESS_MEMBER_SIGNUP(201,"M001","멤버를 정상적으로 등록했습니다."),
    API_SUCCESS_MEMBER_SIGNIN(200,"M002","정상적으로 로그인했습니다."),

    API_SUCCESS_PRECEDENT_SEARCH(200,"P001","검색을 정상적으로 실행했습니다.")

    ;
    private final int status;
    private final String businessCode;
    private final String message;
}
