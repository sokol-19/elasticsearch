package store.sokolov.es.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Сущность отображающая верхний уровень категорий
 */
@Document(indexName = "catalog")
@TypeAlias("category")
public class Category {
    @Id
    private Long id;
    @Field(name = "name", type = FieldType.Text)
    private String name;

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
}
