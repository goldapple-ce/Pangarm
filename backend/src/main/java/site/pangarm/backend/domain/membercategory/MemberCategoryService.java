package site.pangarm.backend.domain.membercategory;

import site.pangarm.backend.domain.category.entity.Category;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.membercategory.entity.MemberCategory;

import java.util.List;

public interface MemberCategoryService {

    void save(Member member, Category category);

    void delete(int memberId, int categoryId);

    MemberCategory findByMemberIdAndCategoryId(int memberId, int categoryId);

    List<MemberCategory> findByMemberId(int memberId);

    List<String> getCategoryNameList(List<MemberCategory> memberCategoryList);

}
