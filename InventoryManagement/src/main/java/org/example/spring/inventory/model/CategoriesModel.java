package org.example.spring.inventory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriesModel {

    @JsonProperty(value = "id")
    private Long categoryId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "products")
    private List<ProductsModel> products;
}
