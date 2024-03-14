package site.pangarm.backend.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Global
    API_ERROR_INTERNAL_SERVER_ERROR(500, "G001", "서버 오류"),
    API_ERROR_INPUT_INVALID_VALUE(409, "G002", "잘못된 입력"),
    API_ERROR_NOT_FOUND(400,"G003","존재하지 않는 데이터"),


    ;

    private final int status;
    private final String code;
    private final String message;
}
