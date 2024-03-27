package site.pangarm.backend.domain.news;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

interface NewsRepository extends ElasticsearchRepository<News, String> {

}
