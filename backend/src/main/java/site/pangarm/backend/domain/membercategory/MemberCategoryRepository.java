package site.pangarm.backend.domain.membercategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberCategoryRepository extends JpaRepository<MemberCategory,Integer> {

    @Query("select mc from MemberCategory mc where mc.member.id = :memberId and mc.category.id = :categoryId")
    void deleteByMemberIdAndCategoryId(int memberId, int categoryId);
}
