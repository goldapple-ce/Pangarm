package site.pangarm.backend.domain.searchHistoryPrecedent;

import site.pangarm.backend.global.error.ErrorCode;
import site.pangarm.backend.global.error.exception.BusinessException;

public class SearchHistoryPrecedentException extends BusinessException {
    public SearchHistoryPrecedentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
