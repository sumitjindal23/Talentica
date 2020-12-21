package org.example.spring.inventory.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "products")
public class Products {

    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "name")
    private String name;

    @ManyToOne(targetEntity = Categories.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Categories categories;

    /**
     * Last updated date maps with lastupdateddatetime column
     */
    @Column(name = "last_updated_datetime")
    private Timestamp lastUpdatedDateTime;

    /**
     * Last updated by User maps with lastupdateduser column
     */

    @Column(name = "last_updated_username")
    private String lastUpdatedUserName;

    /**
     * Flag for checking valid SRL of the function
     */
    @Column(name = "deleted")
    @Type(type = "yes_no")
    private Boolean deleted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Objects.equals(productId, products.productId) && Objects.equals(name, products.name) && Objects.equals(categories, products.categories) && Objects.equals(lastUpdatedDateTime, products.lastUpdatedDateTime) && Objects.equals(lastUpdatedUserName, products.lastUpdatedUserName) && Objects.equals(deleted, products.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name, categories, lastUpdatedDateTime, lastUpdatedUserName, deleted);
    }
}
