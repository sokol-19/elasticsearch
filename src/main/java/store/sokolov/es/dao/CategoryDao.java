package store.sokolov.es.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import store.sokolov.es.pojo.Category;

import java.util.List;

@Repository
public interface CategoryDao extends ElasticsearchRepository<Category, Long> {
    List<Category> findByName(String name);
}
