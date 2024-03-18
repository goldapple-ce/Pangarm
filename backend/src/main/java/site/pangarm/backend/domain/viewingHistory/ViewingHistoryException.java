package site.pangarm.backend.domain.viewingHistory;

import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.exception.BusinessException;

public class ViewingHistoryException extends BusinessException {
    public ViewingHistoryException(ErrorCode errorCode) {
        super(errorCode);
    }
}
