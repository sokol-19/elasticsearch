package store.sokolov.es.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.sokolov.es.pojo.Category;
import store.sokolov.es.services.CategoryService;

import java.util.List;

/**
 * Контроллер обслуживает запросы по категориям товаров.
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    public static Logger logger = LoggerFactory.getLogger(CategoryController.class);
    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Обрабатывает поисковый запрос по указанному в параметре значению в названиях категорий товаров.
     * @param name поисковое значение
     * @return список категорий товаров {@link Category}
     */
    @GetMapping
    public List<Category> getCategoriesByName(@RequestParam(value = "name", required = true) String name) {
        logger.info("getCategoriesByName: Старт");
        logger.trace("Входные параметры: name = {}", name);
        return categoryService.findByName(name);
    }

}
