package site.pangarm.backend.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.pangarm.backend.application.facade.NewsFacade;
import site.pangarm.backend.domain.news.entity.News;
import site.pangarm.backend.global.response.api.ApiResponse;
import site.pangarm.backend.global.response.api.ResponseCode;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsFacade newsFacade;

    @Deprecated
    @PostMapping
    public void save(@RequestBody News news){
        newsFacade.registerNews(news);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Iterable<News>>> findAll(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(ResponseCode.API_SUCCESS_NEWS_FIND_ALL, newsFacade.findAllNews(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<News>> findById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(ResponseCode.API_SUCCESS_NEWS_FIND_BY_ID, newsFacade.findNewsById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        newsFacade.deleteNewsById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(ResponseCode.API_SUCCESS_NEWS_DELETE_BY_ID));
    }

    @Deprecated
    @DeleteMapping("/all")
    public ResponseEntity<ApiResponse<Void>> deleteAll() {
        newsFacade.deleteAllNews();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(ResponseCode.API_SUCCESS_NEWS_DELETE_ALL));
    }

}
