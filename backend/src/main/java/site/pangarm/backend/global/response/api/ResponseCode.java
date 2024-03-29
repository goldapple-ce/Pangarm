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
    API_SUCCESS_MEMBER_BOOKMARK(200,"M003","북마크를 정상적으로 실행했습니다."),
    //NEWS
    API_SUCCESS_NEWS_FIND_ALL(200, "N001", "모든 뉴스를 조회했습니다."),
    API_SUCCESS_NEWS_FIND_BY_ID(200, "N002", "해당 뉴스를 조회했습니다."),
    API_SUCCESS_NEWS_DELETE_BY_ID(200, "N003", "해당 뉴스를 삭제했습니다."),
    API_SUCCESS_NEWS_DELETE_ALL(200, "N004", "모든 뉴스를 삭제했습니다."),

    //PRECEDENT
    API_SUCCESS_PRECEDENT_SEARCH(200,"P001","검색을 정상적으로 실행했습니다."),
    API_SUCCESS_PRECEDENT_SEARCH_BOOKMARKED(200,"P002","북마크된 판례를 정상적으로 조회했습니다."),
    API_SUCCESS_PRECEDENT_SEARCH_VIEWED(200,"P003","본 판례를 정상적으로 조회했습니다."),
    API_SUCCESS_PRECEDENT_SEARCH_SUMMARY(200,"P004","판례를 정상적으로 조회했습니다."),
    API_SUCCESS_PRECEDENT_SEARCH_DETAIL(200,"P005","판례를 정상적으로 조회했습니다."),
    ;
    private final int status;
    private final String businessCode;
    private final String message;
}
