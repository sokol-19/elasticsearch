package store.sokolov.es.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.*;
import org.springframework.data.elasticsearch.core.join.JoinField;

/**
 * Сущность отображающая второй уровень категорий
 */
@Document(indexName = "catalog")
@TypeAlias("category_child")
public class CategoryChild {
    @Id
    private Long id;
    @Field(name = "name", type = FieldType.Text)
    private String name;
    @JoinTypeRelations(
            relations = {
                    @JoinTypeRelation(parent = "category", children = "category_child")}
    )
    private JoinField<Long> category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JoinField<Long> getCategory() {
        return category;
    }

    public void setCategory(JoinField<Long> category) {
        this.category = category;
    }
}
