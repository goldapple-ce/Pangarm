package site.pangarm.backend.domain.news;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import site.pangarm.backend.domain.news.entity.News;

import java.util.List;

interface NewsRepository extends ElasticsearchRepository<News, String> {

    List<News> findAllByOrderByCreatedAtDesc(Pageable pageable);

    List<News> findByCategoryListInOrderByCreatedAtDesc(String category, Pageable pageable);

}
