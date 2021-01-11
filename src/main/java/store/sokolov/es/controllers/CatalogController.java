package store.sokolov.es.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.sokolov.es.services.CatalogService;

import java.io.IOException;

/**
 * Контроллер обрабатывает запросы по каталогу товаров (получение каталога товара и сохранение его ElasticSearch)
 *
 */
@RestController
@RequestMapping("catalog")
public class CatalogController {
    public static Logger logger = LoggerFactory.getLogger(CatalogController.class);
    CatalogService catalogService;

    @Autowired
    CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    /**
     * Получение каталога товаров.
     * @return каталог товаров
     * @throws IOException выбрасывается при ошибке связанной с чтением товаров.
     */
    @GetMapping("/get")
    public String getCatalog() throws IOException {
        logger.info("getCatalog: Старт");
        return catalogService.getCatalog();
    }

    /**
     * Получает и загружает каталог товаров ElasticSearch.
     * @return True, если все прошло успешно.
     */
    @GetMapping("/import")
    public boolean importCatalog() {
        logger.info("importCatalog: Старт");
        return catalogService.importCatalog();
    }
}
