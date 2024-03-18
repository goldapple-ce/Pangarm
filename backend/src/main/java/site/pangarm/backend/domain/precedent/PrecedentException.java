package site.pangarm.backend.domain.precedent;

import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.exception.BusinessException;

public class PrecedentException extends BusinessException {
    public PrecedentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
