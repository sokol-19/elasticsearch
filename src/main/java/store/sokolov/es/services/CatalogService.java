package store.sokolov.es.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import store.sokolov.es.pojo.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

/**
 * Сервис по работе с каталогом товаров
 */
@Service
public class CatalogService {
    public static Logger logger = LoggerFactory.getLogger(CatalogService.class);
    private Catalog catalog;
    private String catalogUrl;
    private CategoryService categoryService;
    private CategoryChildService categoryChildService;
    private ProductService productService;

    @Autowired
    public CatalogService(Catalog catalog
            , CategoryService categoryService
            , CategoryChildService categoryChildService
            , ProductService productService) {
        this.catalog = catalog;
        this.categoryService = categoryService;
        this.categoryChildService = categoryChildService;
        this.productService = productService;
    }

    /**
     * Получает каталог товаров по указанному адресу
     *
     * @return каталог товаров
     * @throws IOException
     */
    public String getCatalog() throws IOException {
        logger.info("getCatalog(): Старт");
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(catalogUrl);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            }
        } catch (IOException exception) {
            logger.error("Ошибка при чтении каталога", exception);
            throw exception;
        }
        return sb.toString();
    }

    /**
     * Импорт каталога товаров в ElasticSearch
     *
     * @return true, если все прошло успешно
     */
    public boolean importCatalog() {
        logger.info("importCatalog(): Старт");
        try {
            CatalogTuple<List<Category>, List<CategoryChild>, List<Product>> catalogTuple = catalog.parseXML(catalogUrl);
            categoryService.createCategoryIndexBulk(catalogTuple.categoryList);
            categoryChildService.createCategoryChildIndexBulk(catalogTuple.categoryChildList);
            productService.createProductIndexBulk(catalogTuple.productList);
        } catch (Exception exception) {
            logger.error("Ошибка при импорте каталога товаров", exception);
            return false;
        }
        return true;
    }

    public String getCatalogUrl() {
        return catalogUrl;
    }

    @Value("${catalog.url}")
    public void setCatalogUrl(String catalogUrl) {
        this.catalogUrl = catalogUrl;
    }
}
