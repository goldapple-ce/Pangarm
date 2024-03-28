package site.pangarm.backend.domain.membercategory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.domain.category.entity.Category;
import site.pangarm.backend.domain.member.entity.Member;
import site.pangarm.backend.domain.membercategory.entity.MemberCategory;
import site.pangarm.backend.global.error.ErrorCode;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberCategoryServiceImpl implements MemberCategoryService{

    private final MemberCategoryRepository memberCategoryRepository;

    @Transactional
    @Override
    public void save(Member member, Category category) throws MemberCategoryException {
        if(findByMemberIdAndCategoryId(member.getId(), category.getId()) != null){
            throw new MemberCategoryException(ErrorCode.API_ERROR_ALREADY_EXIST);
        }
        memberCategoryRepository.save(new MemberCategory(member, category));
    }

    @Override
    public void delete(int memberId, int categoryId) {
        memberCategoryRepository.deleteByMemberIdAndCategoryId(memberId, categoryId);
    }

    @Override
    public MemberCategory findByMemberIdAndCategoryId(int memberId, int categoryId){
        return memberCategoryRepository.findByMemberIdAndCategoryId(memberId, categoryId);
    }

    @Override
    public List<MemberCategory> findByMemberId(int memberId) {
        return memberCategoryRepository.findByMemberId(memberId);
    }

}
