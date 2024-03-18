package site.pangarm.backend.domain.viewingHistory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.member.Member;
import site.pangarm.backend.domain.precedent.Precedent;
import site.pangarm.backend.domain.precedentBookmark.PrecedentBookmark;
import site.pangarm.backend.global.error.ErrorCode;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ViewingHistoryService {
    private final ViewingHistoryRepository viewingHistoryRepository;

    @Transactional
    public ViewingHistory save(Member member, Precedent precedent){
        return viewingHistoryRepository.save(validation(ViewingHistory.of(member,precedent)));
    }

    public ViewingHistory findById(ViewingHistoryId viewingHistoryId){
        return viewingHistoryRepository.findById(viewingHistoryId).orElseThrow(()->
            new ViewingHistoryException(ErrorCode.API_ERROR_NOT_FOUND)
        );
    }

    private ViewingHistory validation(ViewingHistory viewingHistory){
        if(viewingHistoryRepository.existsById(viewingHistory.getId()))
            throw new ViewingHistoryException(ErrorCode.API_ERROR_ALREADY_EXIST);
        return viewingHistory;
    }
}
