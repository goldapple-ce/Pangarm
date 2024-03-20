package site.pangarm.backend.domain.news;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
public class News {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //뉴스아이디

    @Column(nullable = false)
    private String newsLink; //뉴스링크

    @Column(nullable = false)
    private String title; //제목

    private String content; //내용

    private String imageUrl; //이미지 링크

    private String author; //기자

    private String createdAt; //작성일
}
