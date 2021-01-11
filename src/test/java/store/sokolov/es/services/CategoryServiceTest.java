package store.sokolov.es.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import store.sokolov.es.ESClientConfig;
import store.sokolov.es.pojo.Category;

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = {ESClientConfig.class})
class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;

    @Test
    void findByName() {
//        List<Category> categoryList = categoryService.findByName("test");
//        System.out.println(categoryList.toString());
    }
}