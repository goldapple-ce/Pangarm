package site.pangarm.backend.global.error.exception;

import lombok.Getter;
import site.pangarm.backend.global.error.ErrorCode;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public static BusinessException of(ErrorCode errorCode){
        return new BusinessException(errorCode);
    }
}
