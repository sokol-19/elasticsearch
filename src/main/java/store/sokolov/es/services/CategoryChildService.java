package store.sokolov.es.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.sokolov.es.dao.CategoryChildDao;
import store.sokolov.es.pojo.CategoryChild;

import java.util.List;

/**
 * Сервис по работу со вторым уровнем категорий товаров {@link CategoryChild}
 */
@Service
public class CategoryChildService {
    public static Logger logger = LoggerFactory.getLogger(CategoryChildService.class);
    final private CategoryChildDao categoryChildDao;

    @Autowired
    public CategoryChildService(CategoryChildDao categoryChildDao) {
        this.categoryChildDao = categoryChildDao;
    }

    public void createCategoryChildIndexBulk(List<CategoryChild> categoryChildList) {
        categoryChildDao.saveAll(categoryChildList);
    }

    public void createCategoryChildIndex(CategoryChild categoryChild) {
        categoryChildDao.save(categoryChild);
    }

    public List<CategoryChild> findByName(String name) {
        return categoryChildDao.findByName(name);
    }
}
