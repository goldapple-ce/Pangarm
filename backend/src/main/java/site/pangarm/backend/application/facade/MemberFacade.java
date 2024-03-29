package site.pangarm.backend.application.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.dto.request.MemberSignUpRequest;
import site.pangarm.backend.application.dto.response.PrecedentSearchHistoryResponse;
import site.pangarm.backend.domain.member.MemberException;
import site.pangarm.backend.domain.member.MemberService;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.precedent.PrecedentService;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.precedentBookmark.PrecedentBookmarkService;
import site.pangarm.backend.domain.searchHistory.SearchHistoryService;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;
import site.pangarm.backend.global.error.ErrorCode;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberFacade {
    private final MemberService memberService;
    private final BCryptPasswordEncoder encoder;
    private final PrecedentBookmarkService bookmarkService;
    private final SearchHistoryService searchHistoryService;
    private final PrecedentService precedentService;

    @Transactional
    public void signup(MemberSignUpRequest request){
        memberService.save(request.toEntity(encoder));
    }

    public PrecedentSearchHistoryResponse findAllSearchHistory(User user){
        Member member = memberService.findById(Integer.parseInt(user.getUsername()));
        List<SearchHistory> searchHistoryList = searchHistoryService.findAllByMember(member);
        return PrecedentSearchHistoryResponse.of(searchHistoryList);
    }

    @Transactional
    public void bookmarkPrecedent(User user, int precedentId){
        Member member = memberService.findById(Integer.parseInt(user.getUsername()));
        Precedent precedent = precedentService.findById(precedentId);

        bookmarkService.update(member,precedent);
    }

}
