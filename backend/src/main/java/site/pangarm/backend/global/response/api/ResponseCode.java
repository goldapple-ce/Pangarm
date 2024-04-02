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
    API_SUCCESS_MEMBER_BOOKMARK(200,"M004","북마크를 정상적으로 실행했습니다."),
    API_SUCCESS_MEMBER_GET_BY_ID(200,"M003","정상적으로 멤버를 조회했습니다."),

    //NEWS
    API_SUCCESS_NEWS_FIND_ALL(200, "N001", "모든 뉴스를 조회했습니다."),
    API_SUCCESS_NEWS_FIND_ALL_BY_CATEGORY(200, "N001", "해당 카테고리의 뉴스를 조회했습니다."),
    API_SUCCESS_NEWS_FIND_BY_ID(200, "N002", "해당 뉴스를 조회했습니다."),
    API_SUCCESS_NEWS_DELETE_BY_ID(200, "N003", "해당 뉴스를 삭제했습니다."),
    API_SUCCESS_NEWS_DELETE_ALL(200, "N004", "모든 뉴스를 삭제했습니다."),

    //NEWS_CATEGORY
    API_SUCCESS_CATEGORY_FIND_ALL(200,"NC001","모든 카테고리를 조회했습니다."),
    API_SUCCESS_MEMBER_SUBSCRIBE(201,"NC002","해당 카테고리를 구독했습니다."),
    API_SUCCESS_MEMBER_UNSUBSCRIBE(200,"NC003","해당 카테고리를 구독해지했습니다."),
    API_SUCCESS_MEMBER_CATEGORY_LIST(200,"NC004","해당 멤버의 카테고리 리스트를 조회했습니다."),

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
