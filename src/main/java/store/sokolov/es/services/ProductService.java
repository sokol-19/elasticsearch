package store.sokolov.es.services;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import store.sokolov.es.dao.ProductDao;
import store.sokolov.es.pojo.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис по работе с продуктами {@link Product}
 */
@Service
public class ProductService {
    public static Logger logger = LoggerFactory.getLogger(ProductService.class);
    final private ProductDao productDao;
    final private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public ProductService(ProductDao productDao, ElasticsearchOperations elasticsearchOperations) {
        this.productDao = productDao;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    /**
     * Поиск товаров по указанному значению в полях <tt>name</tt> и <tt>description</tt>
     * @param name поисковое значение
     * @return список товаров
     */
    public List<Product> findProductsOnCategory(String name) {
        return findProductsWithFilterOnCategory(name, null);
    }

    /**
     * Поиск товаров по указанному значению в полях <tt>name</tt> и <tt>description</tt> и фильтрацией по полю <tt>category</tt>
     * @param name поисковое значение
     * @param categoryId значение фильтаа
     * @return список товаров
     */
    public List<Product> findProductsWithFilterOnCategory(String name, Long categoryId) {
        logger.info("findProductsWithFilterOnCategory: старт");
        logger.trace("Входные параметры: name = {}, categoryId = {}", name, categoryId);
        BoolQueryBuilder qb = QueryBuilders.boolQuery()
                .must(QueryBuilders.multiMatchQuery(name, "name", "description"));
        if (categoryId != null) {
            qb.filter(QueryBuilders.termQuery("category.parent", categoryId));
        }
        Query query = new NativeSearchQuery(qb);

        SearchHits<Product> searchHits = elasticsearchOperations.search(query, Product.class);
        List<Product> productList = new ArrayList<>();
        for (SearchHit<Product> item : searchHits.getSearchHits()) {
            productList.add(item.getContent());
        }
        return productList;
    }

    /**
     * Поиск товаров по указанному значению в полях <tt>name</tt> и <tt>description</tt> и фильтрацией по полю <tt>category</tt>.
     * Используется прямой запрос в нотации ElasticSearch.
     * @param name поисковое значение
     * @param categoryId значение фильтаа
     * @return список товаров
     */
    public List<Product> findProductsWithFilterOnCategoryNative(String name, Long categoryId) {
        return productDao.findProductsWithFilterOnCategoryUsingCustomNative(name, categoryId);
    }

    public void createProductIndexBulk(List<Product> productList) {
        productDao.saveAll(productList);
    }

    public void createProductIndex(Product product) {
        productDao.save(product);
    }

    public List<Product> findByName(String name) {
        return productDao.findByName(name);
    }
}
