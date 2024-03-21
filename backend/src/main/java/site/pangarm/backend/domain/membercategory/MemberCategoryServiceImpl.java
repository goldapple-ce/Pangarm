package site.pangarm.backend.domain.membercategory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.category.Category;
import site.pangarm.backend.domain.category.CategoryService;
import site.pangarm.backend.domain.member.Member;
import site.pangarm.backend.domain.member.MemberService;
import site.pangarm.backend.global.error.exception.BusinessException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberCategoryServiceImpl implements MemberCategoryService{

    private final MemberCategoryRepository memberCategoryRepository;
    private final MemberService memberService;
    private final CategoryService categoryService;

    @Transactional
    @Override
    public void save(int memberId, int categoryId) throws BusinessException {
        // 회원이 존재하지 않거나 카테고리가 존재하지 않다면 에러 발생
        Member member = memberService.findById(memberId);
        Category category = categoryService.findById(categoryId);
        MemberCategory memberCategory = new MemberCategory(member, category);
        memberCategoryRepository.save(memberCategory);
    }

    @Override
    public void delete(int memberId, int categoryId) {
        memberCategoryRepository.deleteByMemberIdAndCategoryId(memberId, categoryId);
    }

}
