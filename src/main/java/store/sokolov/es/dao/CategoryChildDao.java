package store.sokolov.es.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import store.sokolov.es.pojo.CategoryChild;

import java.util.List;

@Repository
public interface CategoryChildDao extends ElasticsearchRepository<CategoryChild, Long> {
    List<CategoryChild> findByName(String name);
}
