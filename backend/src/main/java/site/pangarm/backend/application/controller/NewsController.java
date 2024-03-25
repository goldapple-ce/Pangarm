package site.pangarm.backend.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import site.pangarm.backend.domain.news.News;
import site.pangarm.backend.domain.news.NewsService;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    public void save(@RequestBody News news){
        newsService.save(news);
    }

    @GetMapping
    public Iterable<News> findAll(){
        return newsService.findAll();
    }

    @GetMapping("/{id}")
    public News findById(@PathVariable String id) {
        return newsService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        newsService.deleteById(id);
    }

    @DeleteMapping("/all")
    public void delete() {
        newsService.deleteAll();
    }

}
