package site.pangarm.backend.domain.member;

import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.exception.BusinessException;

public class MemberException extends BusinessException {
    public MemberException(ErrorCode errorCode) {
        super(errorCode);
    }
}
