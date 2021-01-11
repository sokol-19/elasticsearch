package store.sokolov.es.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import store.sokolov.es.ESClientConfig;
import store.sokolov.es.pojo.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {ESClientConfig.class})
class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Test
    void findProductsWithFilterOnCategoryNative() {
//        List<Product> productList = productService.findProductsWithFilterOnCategoryNative("курица", 22L);
//        System.out.println(productList.size());
    }

    @Test
    void findByName() {
//        List<Product> productList = productService.findByName("Ланч с курицей");
//        System.out.println(productList.size());
    }

    @Test
    void findProductsWithFilterOnCategory() {
//        List<Product> productList = productService.findProductsWithFilterOnCategory("курица", 22L);
//        System.out.println(productList.size());
    }
}