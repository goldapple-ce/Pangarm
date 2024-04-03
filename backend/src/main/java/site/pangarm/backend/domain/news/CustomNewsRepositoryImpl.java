package site.pangarm.backend.domain.news;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;
import site.pangarm.backend.domain.news.entity.News;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomNewsRepositoryImpl implements CustomNewsRepository {

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<News> searchNews(String keyword, Pageable pageable) {
        Criteria criteria = new Criteria("title").contains(keyword).or(new Criteria("content").contains(keyword));
        Query query = new CriteriaQuery(criteria).setPageable(pageable);
        SearchHits<News> searchHits = elasticsearchOperations.search(query, News.class);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
