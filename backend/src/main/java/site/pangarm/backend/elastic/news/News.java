package site.pangarm.backend.elastic.news;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

//@Builder
//@AllArgsConstructor @NoArgsConstructor
//@Entity
@Document(indexName = "news")
public class News {

//    @Id
//    private String id;

    @Id
    private Integer id; //뉴스아이디

    private String newsLink; //뉴스링크

    private String title; //제목

    private String content; //내용

    private String imageUrl; //이미지 링크

    private String author; //기자

    private String createdAt; //작성일
}
