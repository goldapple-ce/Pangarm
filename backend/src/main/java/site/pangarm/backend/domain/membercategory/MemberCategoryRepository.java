package site.pangarm.backend.domain.membercategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.pangarm.backend.domain.membercategory.entity.MemberCategory;

import java.util.List;

public interface MemberCategoryRepository extends JpaRepository<MemberCategory,Integer> {

    void deleteByMemberIdAndCategoryId(int memberId, int categoryId);

    MemberCategory findByMemberIdAndCategoryId(int memberId, int categoryId);

    @Query("select mc from MemberCategory mc join fetch mc.category c where mc.member.id = :memberId")
    List<MemberCategory> findByMemberId(int memberId);
}
