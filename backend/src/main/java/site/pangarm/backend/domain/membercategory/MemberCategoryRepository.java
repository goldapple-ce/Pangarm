package site.pangarm.backend.domain.membercategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import site.pangarm.backend.domain.membercategory.entity.MemberCategory;
import java.util.Optional;

public interface MemberCategoryRepository extends JpaRepository<MemberCategory,Integer> {

    @Query("select mc from MemberCategory mc where mc.member.id = :memberId and mc.category.id = :categoryId")
    void deleteByMemberIdAndCategoryId(int memberId, int categoryId);

    MemberCategory findByMemberIdAndCategoryId(int memberId, int categoryId);
}
