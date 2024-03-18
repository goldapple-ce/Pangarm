package site.pangarm.backend.domain.newscategory;

import jakarta.persistence.*;
import site.pangarm.backend.domain.category.Category;
import site.pangarm.backend.domain.news.News;

@Entity
@IdClass(NewsCategoryId.class)
public class NewsCategory {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id", nullable = false)
    private News newsId; //뉴스카테고리 아이디

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category categoryId; //카테고리 이름

}
