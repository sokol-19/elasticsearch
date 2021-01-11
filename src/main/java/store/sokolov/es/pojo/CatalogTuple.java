package store.sokolov.es.pojo;

/**
 * Класс для хранения кортежа значений.
 * Используется для хранения каталога продуктов.
 * @param <A> список верхних категорий товаров
 * @param <B> список категорий товаров второго уровня
 * @param <C> список продуктов
 */
public class CatalogTuple<A, B, C> {
    public final A categoryList;
    public final B categoryChildList;
    public final C productList;

    public CatalogTuple(A categoryList, B categoryChildList, C productList) {
        this.categoryList = categoryList;
        this.categoryChildList = categoryChildList;
        this.productList = productList;
    }
}
