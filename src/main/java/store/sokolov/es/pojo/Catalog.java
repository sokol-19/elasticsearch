package store.sokolov.es.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.join.JoinField;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import store.sokolov.es.services.ProductService;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.*;

/**
 * Класс, реализующий парсинг xml с категориями и продуктами с сохранением их в кортеже {@link CatalogTuple}
 * В качестве парсера используется SAX
 * @see SAXParser
 * @see DefaultHandler
 */
@Component
public class Catalog {
    public static Logger logger = LoggerFactory.getLogger(Catalog.class);
    private Map<Long, Category> longCategoryMap  = new HashMap<>();
    private Map<Long, CategoryChild> longCategoryChildMap  = new HashMap<>();
    private Map<Long, Product> longProductMap  = new HashMap<>();

    /**
     * Стартовый метод для разбора xml с каталогом.
     * Категории продуктов будут сохранены в мапу <strong>categoryList</strong>, а продукты в <strong>productList</strong>
      * @param url
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public CatalogTuple<List<Category>, List<CategoryChild>, List<Product>> parseXML(String url) throws ParserConfigurationException, SAXException, IOException {
        logger.info("parseXML(): старт");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(url, handler);

        CatalogTuple<List<Category>, List<CategoryChild>, List<Product>> catalogTuple =
                new CatalogTuple<>(
                        new ArrayList<>(longCategoryMap.values()),
                        new ArrayList<>(longCategoryChildMap.values()),
                        new ArrayList<>(longProductMap.values()));
        return catalogTuple;
    }

    /**
     * Реализация хандлера для разбора xml
     */
    private class XMLHandler extends DefaultHandler {
        String lastElementName;
        Long categoryId;
        Long productId;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            //super.startElement(uri, localName, qName, attributes);
            lastElementName = qName;
            if ("category".equals(lastElementName)) {
                categoryId = Long.parseLong(attributes.getValue("id"));
                String stringParentId = attributes.getValue("parentId");
                if (stringParentId == null || stringParentId.isEmpty()) {
                    // Category
                    Category category = new Category();
                    category.setId(categoryId);
                    longCategoryMap.put(categoryId, category);
                } else {
                    // CategoryChild
                    CategoryChild categoryChild = new CategoryChild();
                    categoryChild.setId(categoryId);
                    categoryChild.setCategory(new JoinField<>("category", Long.parseLong(stringParentId)));
                    longCategoryChildMap.put(categoryId, categoryChild);
                }
            } else if ("product".equals(lastElementName)) {
                // Product
                productId  = Long.parseLong(attributes.getValue("id"));
                Product product = new Product();
                product.setId(productId);
                longProductMap.put(productId, product);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String text = new String(ch, start, length);
            text = text.replace("\n", "").trim();

            if (!text.isEmpty()) {
                switch (lastElementName) {
                    case "category":
                        if (longCategoryMap.get(categoryId) != null) {
                            // Category
                            longCategoryMap.get(categoryId).setName(text);
                        } else {
                            // CategoryChild
                            longCategoryChildMap.get(categoryId).setName(text);
                        }
                        break;
                    case "category_id":
                        Long id = Long.parseLong(text);
                        String catalogType = (longCategoryMap.get(id) != null ? "category" : "category_child");
                        longProductMap.get(productId).setCategory(new JoinField<>(catalogType, id));
                        break;
                    case "name":
                        longProductMap.get(productId).setName(text);
                        break;
                    case "description":
                        longProductMap.get(productId).setDescription(text);
                        break;
                    case "price":
                        longProductMap.get(productId).setPrice(Long.parseLong(text));
                        break;
                    case "picture":
                        longProductMap.get(productId).setPicture(text);
                        break;
                }
            }
        }
    }
}
