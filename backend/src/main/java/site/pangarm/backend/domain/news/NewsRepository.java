package site.pangarm.backend.domain.news;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import site.pangarm.backend.domain.news.entity.News;

interface NewsRepository extends ElasticsearchRepository<News, String> {

}
