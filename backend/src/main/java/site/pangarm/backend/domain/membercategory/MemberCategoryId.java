package site.pangarm.backend.domain.membercategory;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class MemberCategoryId implements Serializable {
    private Integer memberId;
    private Integer categoryId;
}
