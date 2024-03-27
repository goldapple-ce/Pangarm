package site.pangarm.backend.domain.news;

import org.springframework.data.domain.Pageable;
import site.pangarm.backend.domain.news.entity.News;

import java.util.List;

public interface NewsService {

    void save(News news);

    List<News> findAll(Pageable pageable);

    List<News> findAllByCategory(String category, Pageable pageable);

    News findById(String id);

    void deleteById(String id);

    void deleteAll();
}
