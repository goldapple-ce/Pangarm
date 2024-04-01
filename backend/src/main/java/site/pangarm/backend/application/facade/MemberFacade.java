package site.pangarm.backend.application.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.dto.request.MemberSignUpRequest;
import site.pangarm.backend.application.dto.response.MemberFindByIdResponse;
import site.pangarm.backend.application.dto.response.PrecedentSearchHistoryResponse;
import site.pangarm.backend.domain.category.CategoryService;
import site.pangarm.backend.domain.category.entity.Category;
import site.pangarm.backend.domain.member.MemberService;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.membercategory.MemberCategoryService;
import site.pangarm.backend.domain.precedent.PrecedentService;
import site.pangarm.backend.domain.precedent.entity.Precedent;
import site.pangarm.backend.domain.precedentBookmark.PrecedentBookmarkService;
import site.pangarm.backend.domain.searchHistory.SearchHistoryService;
import site.pangarm.backend.domain.searchHistory.entity.SearchHistory;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberFacade {

    private final MemberService memberService;
    private final CategoryService categoryService;
    private final MemberCategoryService memberCategoryService;
    private final BCryptPasswordEncoder encoder;
    private final PrecedentBookmarkService bookmarkService;
    private final SearchHistoryService searchHistoryService;
    private final PrecedentService precedentService;

    @Transactional
    public void signup(MemberSignUpRequest request) {
        memberService.save(request.toEntity(encoder));
    }

    public PrecedentSearchHistoryResponse findAllSearchHistory(User user) {
        Member member = memberService.findByUser(user);
        List<SearchHistory> searchHistoryList = searchHistoryService.findAllByMember(member);
        return PrecedentSearchHistoryResponse.of(searchHistoryList);
    }

    @Transactional
    public void bookmarkPrecedent(User user, int precedentId) {
        Member member = memberService.findByUser(user);
        Precedent precedent = precedentService.findById(precedentId);

        bookmarkService.update(member, precedent);
    }

    public MemberFindByIdResponse getById(int userId) {
        return MemberFindByIdResponse.of(memberService.findById(userId));
    }

    @Transactional
    public void subscribe(int memberId, int categoryId) {
        Member member = memberService.findById(memberId);
        Category category = categoryService.findById(categoryId);
        memberCategoryService.save(member, category);
    }

    @Transactional
    public void unsubscribe(int memberId, int categoryId) {
        memberCategoryService.delete(memberId, categoryId);
    }

    @Transactional
    public List<String> getCategoryList(int memberId) {
        return memberCategoryService.findByMemberId(memberId)
                .stream()
                .map(memberCategory -> memberCategory.getCategory().getName())
                .toList();
    }

}
