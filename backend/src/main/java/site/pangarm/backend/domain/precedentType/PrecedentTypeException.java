package site.pangarm.backend.domain.precedentType;

import site.pangarm.backend.domain.precedent.PrecedentException;
import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.exception.BusinessException;

public class PrecedentTypeException extends BusinessException {
    public PrecedentTypeException(ErrorCode errorCode) {
        super(errorCode);
    }


}
