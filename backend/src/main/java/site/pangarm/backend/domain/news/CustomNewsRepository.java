package site.pangarm.backend.domain.news;

import org.springframework.data.domain.Pageable;
import site.pangarm.backend.domain.news.entity.News;

import java.util.List;

public interface CustomNewsRepository {
    List<News> searchNews(String keyword, Pageable pageable);
}
