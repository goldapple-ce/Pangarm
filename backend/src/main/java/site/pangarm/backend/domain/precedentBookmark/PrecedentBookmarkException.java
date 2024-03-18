package site.pangarm.backend.domain.precedentBookmark;

import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.exception.BusinessException;

public class PrecedentBookmarkException extends BusinessException {
    public PrecedentBookmarkException(ErrorCode errorCode) {
        super(errorCode);
    }
}
