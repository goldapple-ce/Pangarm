package site.pangarm.backend.application.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.dto.request.MemberSignUpRequest;
import site.pangarm.backend.domain.category.CategoryService;
import site.pangarm.backend.domain.category.entity.Category;
import site.pangarm.backend.domain.member.MemberService;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.membercategory.MemberCategoryService;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberFacade {

    private final MemberService memberService;
    private final CategoryService categoryService;
    private final MemberCategoryService memberCategoryService;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void signup(MemberSignUpRequest request){
        memberService.save(request.toEntity(encoder));
    }

    public Member getById(int userId) {
        return memberService.findById(userId);
    }

    @Transactional
    public void subscribe(int memberId, int categoryId){
        Member member = memberService.findById(memberId);
        Category category = categoryService.findById(categoryId);
        memberCategoryService.save(member, category);
    }

}
