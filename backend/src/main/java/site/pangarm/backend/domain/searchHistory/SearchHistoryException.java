package site.pangarm.backend.domain.searchHistory;

import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.exception.BusinessException;

public class SearchHistoryException extends BusinessException {
    public SearchHistoryException(ErrorCode errorCode) {
        super(errorCode);
    }
}
