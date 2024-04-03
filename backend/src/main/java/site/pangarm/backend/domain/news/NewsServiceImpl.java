package site.pangarm.backend.domain.news;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import site.pangarm.backend.domain.news.entity.News;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService{

    private final NewsRepository newsRepository;
    @Override
    public void save(News news) {
        newsRepository.save(news);
    }

    @Override
    public List<News> findAll(Pageable pageable) {
        return newsRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    @Override
    public List<News> findAllByCategory(String category, Pageable pageable) {
        return newsRepository.findByCategoryListInOrderByCreatedAtDesc(category, pageable);
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

    @Override
    public List<News> searchNews(String keyword, Pageable pageable) {
        return newsRepository.searchNews(keyword, pageable);
    }
}
