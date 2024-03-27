package site.pangarm.backend.domain.news;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import site.pangarm.backend.domain.news.entity.News;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;
    @Override
    public void save(News news) {
        newsRepository.save(news);
    }

    @Override
    public Iterable<News> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
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
