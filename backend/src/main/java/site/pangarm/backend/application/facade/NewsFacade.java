package site.pangarm.backend.application.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.dto.request.CategoryRegisterRequest;
import site.pangarm.backend.domain.category.CategoryService;
import site.pangarm.backend.domain.news.entity.News;
import site.pangarm.backend.domain.news.NewsService;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NewsFacade {

    private final CategoryService categoryService;

    private final NewsService newsService;

    public void registerCategory(CategoryRegisterRequest request) {
        categoryService.save(request.toEntity());
    }

    public void registerNews(News news) {
        newsService.save(news);
    }

    public Iterable<News> findAllNews(Pageable pageable) {
        return newsService.findAll(pageable);
    }

    public News findNewsById(String id) {
        return newsService.findById(id);
    }

    public void deleteNewsById(String id) {
        newsService.deleteById(id);
    }

    public void deleteAllNews() {
        newsService.deleteAll();
    }

}
