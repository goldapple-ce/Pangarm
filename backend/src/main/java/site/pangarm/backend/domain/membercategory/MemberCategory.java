package site.pangarm.backend.domain.membercategory;

import jakarta.persistence.*;
import site.pangarm.backend.domain.category.Category;
import site.pangarm.backend.domain.member.Member;

@Entity
@IdClass(MemberCategoryId.class)
public class MemberCategory {
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member memberId; //회원 아이디
    
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryId; //카테고리 아이디
    


}
