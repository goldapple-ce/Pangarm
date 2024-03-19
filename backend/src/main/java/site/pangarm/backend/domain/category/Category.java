package site.pangarm.backend.domain.category;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import site.pangarm.backend.application.category.dto.request.CategoryUpdateRequest;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //뉴스 카테고리 아이디

    @Column(nullable = false, unique = true)
    private String name; //카테고리 이름

    @Builder
    public Category(String name) {
        this.name = name;
    }

    public void update(CategoryUpdateRequest request) {
        this.name = request.name();
    }
}
