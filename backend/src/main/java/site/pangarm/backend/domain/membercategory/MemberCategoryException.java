package site.pangarm.backend.domain.membercategory;

import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.exception.BusinessException;

public class MemberCategoryException extends BusinessException {
    public MemberCategoryException(ErrorCode errorCode) {
        super(errorCode);
    }
}
