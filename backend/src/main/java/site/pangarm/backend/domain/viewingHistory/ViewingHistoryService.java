package site.pangarm.backend.domain.viewingHistory;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.viewingHistory.entity.ViewingHistory;
import site.pangarm.backend.global.error.ErrorCode;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ViewingHistoryService {
    private final ViewingHistoryRepository viewingHistoryRepository;

    @Transactional
    public ViewingHistory save(Member member, Precedent precedent){
        return viewingHistoryRepository.findByMemberAndPrecedent(member, precedent).orElseGet(()->
                viewingHistoryRepository.save(ViewingHistory.of(member,precedent)));
    }

    public ViewingHistory findById(Member member, Precedent precedent){
        return viewingHistoryRepository.findByMemberAndPrecedent(member,precedent).orElseThrow(()->
            new ViewingHistoryException(ErrorCode.API_ERROR_VIEWING_HISTORY_NOT_FOUND)
        );
    }

    public Page<Object[]> findByMember(Member member, Pageable pageable){
        return viewingHistoryRepository.findByMember(member, pageable);
    }
}
