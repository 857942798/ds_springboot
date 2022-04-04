package com.ds.repository;


import com.ds.domain.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;


@Component 
//@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {

	
}
