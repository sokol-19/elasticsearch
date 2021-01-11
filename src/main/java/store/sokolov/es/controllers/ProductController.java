package store.sokolov.es.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import store.sokolov.es.pojo.Product;
import store.sokolov.es.services.ProductService;

import java.util.List;

/**
 * Контроллер обслуживает запросы по продуктам.
 */
@RestController
@RequestMapping("product")
public class ProductController {
    public static Logger logger = LoggerFactory.getLogger(ProductController.class);
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    /**
     * Обрабатывает запросы по продуктам.
     * Поиск осуществляется по значению указанному в <tt>name</tt> по полям <tt>name</tt> и <tt>description</tt>.
     * Параметр <tt>name</tt> обязательный.
     * <p>Если задан параметр <tt>category</tt>, то по полю <tt>category</tt> устанавливается фильтр с переданным значением.
     * @param name поисковое значение
     * @param paramCategoryId фильтр по категории товара
     * @return список продуктов {@link Product}
     */
    @GetMapping
    public List<Product> getProductWithFilter(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "category", required = false) String paramCategoryId) {
        logger.info("getProductWithFilter: Старт");
        logger.trace("Входные параметры: name = {}, category = {}", name, paramCategoryId);
        Long categoryId;
        try {
            if (paramCategoryId == null || paramCategoryId.isEmpty()) {
                categoryId = null;
            } else {
                categoryId = Long.parseLong(paramCategoryId);
            }
        } catch (NumberFormatException e) {
            categoryId = -1L;
        }
        return productService.findProductsWithFilterOnCategory(name, categoryId);
    }

}
