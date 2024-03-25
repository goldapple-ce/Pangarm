package site.pangarm.backend.domain.precedentBookmark;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.precedentBookmark.entity.PrecedentBookmark;
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

    @Transactional
    public PrecedentBookmark save(PrecedentBookmark precedentBookmark){
        return precedentBookmarkRepository.save(validation(precedentBookmark));
    }

    public PrecedentBookmark findById(int id){
        return precedentBookmarkRepository.findById(id).orElseThrow(()->
            new PrecedentBookmarkException(ErrorCode.API_ERROR_NOT_FOUND)
        );
    }

    private PrecedentBookmark validation(PrecedentBookmark precedentBookmark){
        if(precedentBookmarkRepository.existsByMemberAndPrecedent(precedentBookmark.getMember(),precedentBookmark.getPrecedent()))
            throw new PrecedentBookmarkException(ErrorCode.API_ERROR_ALREADY_EXIST);
        return precedentBookmark;
    }
}
