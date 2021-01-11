package store.sokolov.es.dao;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import store.sokolov.es.pojo.Product;

import java.util.List;

@Repository
public interface ProductDao extends ElasticsearchRepository<Product, Long> {
    List<Product> findByName(String name);

    @Query("\"bool\": {\"must\": [{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"name\", \"description\"], \"type\": \"best_fields\"}}], \"filter\": [{\"term\": {\"category.parent\": {\"value\": \"?1\"}}}]}")
    List<Product> findProductsWithFilterOnCategoryUsingCustomNative(String name, Long categoryId);
}
