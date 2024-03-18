package site.pangarm.backend.domain.precedentBookmark;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.member.Member;
import site.pangarm.backend.domain.precedent.Precedent;
import site.pangarm.backend.global.error.ErrorCode;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PrecedentBookmarkService {
    private final PrecedentBookmarkRepository precedentBookmarkRepository;

    @Transactional
    public PrecedentBookmark save(Member member, Precedent precedent){
        return precedentBookmarkRepository.save(validation(PrecedentBookmark.of(member,precedent)));
    }

    public PrecedentBookmark findById(PrecedentBookmarkId precedentBookmarkId){
        return precedentBookmarkRepository.findById(precedentBookmarkId).orElseThrow(()->
            new PrecedentBookmarkException(ErrorCode.API_ERROR_NOT_FOUND)
        );
    }

    private PrecedentBookmark validation(PrecedentBookmark precedentBookmark){
        if(precedentBookmarkRepository.existsById(precedentBookmark.getId()))
            throw new PrecedentBookmarkException(ErrorCode.API_ERROR_ALREADY_EXIST);
        return precedentBookmark;
    }
}
