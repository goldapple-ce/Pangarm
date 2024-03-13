package site.pangarm.backend.domain.category;

import jakarta.persistence.*;

@Entity
public class Category {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //뉴스카테고리 아이디

    @Column(nullable = false)
    private String name; //카테고리 이름
}
