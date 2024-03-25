package site.pangarm.backend.domain.news;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Data
@Document(indexName = "news")
public class News {

    @Id
    private String id; //뉴스아이디

    @Field(type = FieldType.Text)
    private String newsLink; //뉴스링크

    @Field(type = FieldType.Text)
    private String title; //제목

    @Field(type = FieldType.Text)
    private String content; //내용

    @Field(type = FieldType.Text)
    private String imageUrl; //이미지 링크

    @Field(type = FieldType.Text)
    private String author; //기자

    @Field(type = FieldType.Text)
    private String createdAt; //작성일

    @Field(type = FieldType.Keyword) //keyword는
    private List<String> categoryList; //뉴스 카테고리

}
