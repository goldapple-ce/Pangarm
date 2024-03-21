package site.pangarm.backend.domain.category;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.pangarm.backend.application.category.dto.request.CategoryRegisterRequest;
import site.pangarm.backend.application.category.dto.request.CategoryUpdateRequest;

@Getter
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

    public Category update(Category category) {
        this.name = category.getName();
        return this;
    }

    public static Category of(String name) {
        return Category.builder()
                .name(name)
                .build();
    }
}
