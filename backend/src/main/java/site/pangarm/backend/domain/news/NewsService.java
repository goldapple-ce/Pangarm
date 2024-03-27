package site.pangarm.backend.domain.news;

import org.springframework.data.domain.Pageable;

public interface NewsService {
    void save(News news);
    Iterable<News> findAll(Pageable pageable);
    News findById(String id);
    void deleteById(String id);
    void deleteAll();
}
