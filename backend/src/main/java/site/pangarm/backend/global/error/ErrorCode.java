package site.pangarm.backend.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Global
    API_ERROR_INTERNAL_SERVER_ERROR(500, "G001", "서버 오류"),
    API_ERROR_INPUT_INVALID_VALUE(400, "G002", "잘못된 입력"),
    API_ERROR_NOT_FOUND(404,"G003","존재하지 않는 데이터"),
    API_ERROR_ALREADY_EXIST(409,"G004","이미 존재하는 데이터"),

    API_ERROR_AUTH_BY_AUTHORIZATION_INFORMATION(400, "AUTH001", "권한 정보가 없는 토큰입니다."),
    API_ERROR_AUTH_BY_JWT_SIGNATURE_INVALID(401, "AUTH002", "잘못된 서명입니다."),
    API_ERROR_AUTH_BY_JWT_KEY_EXPIERD(401, "AUTH003", "만료된 토큰입니다."),
    API_ERROR_AUTH_BY_JWT_NOT_SUPPORT(401, "AUTH004", "지원하지 않는 토큰입니다."),
    API_ERROR_AUTH_BY_JWT_KEY_INVALID(401, "AUTH005", "잘못된 토큰입니다."),
    API_ERROR_AUTH_BY_AUTH_PERMISSION_TO_ACCESS_THE_REQUEST_ROLE(403, "AUTH006", "해당 기능에 대한 권한이 없습니다."),
    API_ERROR_AUTH_BY_AUTHORIZATION_IS_NECESSARY(401, "AUTH007", "사용자 인증이 필요합니다."),

    API_ERROR_MEMBER_NOT_FOUND(400,"M001","존재하지 않는 멤버"),
    API_ERROR_MEMBER_ALREADY_EXIST(400,"M002","존재하지 않는 멤버"),

    API_ERROR_PRECEDENT_NOT_FOUND(400,"P001","존재하지 않는 판례"),
    API_ERROR_PRECEDENT_ALREADY_EXIST(400,"P002","이미 존재하는 판례"),

    API_ERROR_PRECEDENT_BOOKMARK_NOT_FOUND(400,"PB001","존재하지않는 판례북마크"),
    API_ERROR_PRECEDENT_BOOKMARK_ALREADY_EXIST(400,"PB002","이미 존재하는 판례북마크"),

    API_ERROR_PRECEDENT_TYPE_NOT_FOUND(400,"PT001","존재하지 않는 판례타입"),
    API_ERROR_PRECEDENT_TYPE_ALREADY_EXIST(400,"PT002","이미 존재하는 판례타입"),

    API_ERROR_SEARCH_HISTORY_NOT_FOUND(400,"SH001","존재하지 않는 검색히스토리"),
    API_ERROR_SEARCH_HISTORY_ALREADY_EXIST(400,"SH002","이미 존재하는 검색히스토리"),

    API_ERROR_SEARCH_HISTORY_PRECEDENT_NOT_FOUND(400,"SHP001","존재하지 않는 검색히스토리와 관련된 판례"),
    API_ERROR_SEARCH_HISTORY_PRECEDENT_ALREADY_EXIST(400,"SHP002","이미 존재하는 검색히스토리와 관련된 판례"),

    API_ERROR_VIEWING_HISTORY_NOT_FOUND(400,"VH001","존재하지 않는 본 판례"),
    API_ERROR_VIEWING_HISTORY_ALREADY_EXIST(400,"VHP002","이미 존재하는 본 판례"),
    ;
    private final int status;
    private final String code;
    private final String message;
}
