package site.pangarm.backend.domain.membercategory;

import site.pangarm.backend.domain.category.Category;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.membercategory.entity.MemberCategory;
import site.pangarm.backend.domain.member.Member;

import java.util.Optional;

public interface MemberCategoryService {
    void save(Member member, Category category);
    void delete(int memberId, int categoryId);

    MemberCategory findByMemberIdAndCategoryId(int memberId, int categoryId);

}
