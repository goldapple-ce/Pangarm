package site.pangarm.backend.application.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.pangarm.backend.application.dto.request.CategoryRegisterRequest;
import site.pangarm.backend.domain.category.CategoryService;
import site.pangarm.backend.domain.category.entity.Category;
import site.pangarm.backend.domain.news.entity.News;
import site.pangarm.backend.domain.news.NewsService;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class NewsFacade {

    private final CategoryService categoryService;

    private final NewsService newsService;

    /** News */
    public void registerNews(News news) {
        newsService.save(news);
    }

    public List<News> findAllNews(Pageable pageable) {
        return newsService.findAll(pageable);
    }

    public List<News> findAllNewsByCategory(String category, Pageable pageable) {
        return newsService.findAllByCategory(category, pageable);
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

    /** News Category */
    public void registerCategory(CategoryRegisterRequest request) {
        categoryService.save(request.toEntity());
    }

    public List<String> findAllCategoryList(){
        return categoryService.findAll()
                .stream()
                .map(Category::getName)
                .toList();
    }

}
