package site.pangarm.backend.elastic.news;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface NewsElasticRepository extends ElasticsearchRepository<News, String> {
}
