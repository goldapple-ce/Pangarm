package site.pangarm.backend.domain.news;

public interface NewsService {
    void save(News news);
    Iterable<News> findAll();
    News findById(String id);
    void deleteById(String id);
    void deleteAll();
}
