package site.pangarm.backend.domain.newscategory;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class NewsCategoryId implements Serializable {
    private Integer newsId;
    private Integer categoryId;
}
