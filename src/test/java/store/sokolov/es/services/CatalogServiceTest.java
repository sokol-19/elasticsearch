package store.sokolov.es.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.xml.sax.SAXException;
import store.sokolov.es.ESClientConfig;
import store.sokolov.es.pojo.Catalog;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = {ESClientConfig.class})
class CatalogServiceTest {
    @Autowired
    CatalogService catalogService;

    @Test
    void importCatalog() throws ParserConfigurationException, SAXException, IOException {
        //catalogService.importCatalog();
    }
}