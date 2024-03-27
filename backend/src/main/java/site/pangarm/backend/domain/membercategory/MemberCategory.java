package site.pangarm.backend.domain.membercategory;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import site.pangarm.backend.domain.category.Category;
import site.pangarm.backend.domain.member.Member;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //멤버 카테고리 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; //회원 아이디

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category; //카테고리 아이디

    @Builder
    public MemberCategory(Member member, Category category) {
        addRelatedMember(member);
        addRelatedCategory(category);
    }

    private void addRelatedMember(Member member){
        this.member = member;
        member.getMemberCategoryList().add(this);
    }

    private void addRelatedCategory(Category category){
        this.category = category;
        category.getMemberCategoryList().add(this);
    }

}
