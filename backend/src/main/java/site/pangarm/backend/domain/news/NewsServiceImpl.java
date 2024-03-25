package site.pangarm.backend.domain.news;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;
    @Override
    public void save(News news) {
        newsRepository.save(news);
    }

    @Override
    public Iterable<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News findById(String id) {
        return newsRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        newsRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        newsRepository.deleteAll();
    }
}
