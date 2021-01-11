package store.sokolov.es.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.join.JoinField;

/**
 * Сущность отображающая продукт
 */
@Document(indexName = "catalog")
@TypeAlias("product")
public class Product {
    @Id
    private Long id;
    @JoinTypeRelations(
            relations = {
                    @JoinTypeRelation(parent = "category", children = "product"),
                    @JoinTypeRelation(parent = "category_child", children = "product")}
    )
    private JoinField<Long> category;
    @Field(name = "name", type = FieldType.Text)
    private String name;
    @Field(name = "description", type = FieldType.Text)
    private String description;
    @Field(name = "price", type = FieldType.Long)
    private Long price;
    @Field(name = "picture", type = FieldType.Text)
    private String picture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JoinField<Long> getCategory() {
        return category;
    }

    public void setCategory(JoinField<Long> category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
