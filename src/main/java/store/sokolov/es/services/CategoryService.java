package store.sokolov.es.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.sokolov.es.dao.CategoryDao;
import store.sokolov.es.pojo.Category;

import java.util.List;

/**
 * Сервис по работе с категорией товаров верхнего уровня {@link Category}
 */
@Service
public class CategoryService  {
    public static Logger logger = LoggerFactory.getLogger(CategoryService.class);
    final private CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public void createCategoryIndexBulk(List<Category> categoryList) {
        categoryDao.saveAll(categoryList);
    }

    public void createCategoryIndex(Category category) {
        categoryDao.save(category);
    }

    public List<Category> findByName(String name) {
        return categoryDao.findByName(name);
    }
}
