package site.pangarm.backend.domain.caseType;

import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.exception.BusinessException;

public class CaseTypeException extends BusinessException {
    public CaseTypeException(ErrorCode errorCode) {
        super(errorCode);
    }
}
